package by.zemich.cataloguems.catalogueservice.infrastrucrure.config;

import by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka.deserializer.PayloadDeserializer;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaRetryTopic;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@EnableKafkaRetryTopic
@Configuration
@Slf4j
public class KafkaConfig {
    private final MeterRegistry meterRegistry;
    public KafkaConfig(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Bean
    public ConsumerFactory<String, byte[]> consumerFactory() {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PayloadDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "vk-post-client");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "vk-post-service-group");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "20971520");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        //props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, true);

        DefaultKafkaConsumerFactory<String, byte[]> consumerFactory = new DefaultKafkaConsumerFactory<>(props);
        // observability
//        List<Tag> tags = new ArrayList<>();
//        tags.add(new ImmutableTag("kafka_consumer_name", "vk-post-consumer"));
//        consumerFactory.addListener(new MicrometerConsumerListener<>(meterRegistry, tags));
        return consumerFactory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, byte[]>> kafkaListenerContainerFactory(
            ConsumerFactory<String, byte[]> consumerFactory,
            DefaultErrorHandler errorHandler
    ) {
        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setBatchListener(false);
        factory.setConcurrency(1);
        factory.setCommonErrorHandler(errorHandler);
        factory.getContainerProperties().setPollTimeout(1_000);
        return factory;
    }
    @Bean
    public DefaultErrorHandler errorHandler() {
        DefaultErrorHandler handler = new DefaultErrorHandler((m, e) -> {
            log.info("Failed in processing events \n " +
                    "Cause by: " + e.getCause().getMessage() + "\n" +
                    "Topic is: " + m.topic() + "\n" +
                    "Offset is: " + m.offset() + "\n" +
                    "TimeStamp is: " + m.timestamp() + "\n" +
                    "Object is: " + m.value() + "\n"
            );

        }, new FixedBackOff(10L, 3L));
        handler.addNotRetryableExceptions(IllegalArgumentException.class);
        return handler;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }


//    @Bean
//    public RetryTopicConfiguration retryTopicConfiguration(KafkaTemplate<String, byte[]> template) {
//        return RetryTopicConfigurationBuilder
//                .newInstance()
//                .fixedBackOff(2000)
//                .timeoutAfter(5000)
//                .maxAttempts(3)
//                .autoCreateTopics(true, 1, (short) 1)
//                .includeTopics(List.of("auditServiceEvents"))

//                .create(template);
//    }



}
