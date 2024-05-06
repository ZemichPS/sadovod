package by.zemich.textprocessormicroservice.infrastracture.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Configuration
public class ModelMapperConfiguration {


    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        Converter<Timestamp, LocalDateTime> timestampLocalDateTimeConverter = new AbstractConverter<>() {
            @Override
            protected LocalDateTime convert(Timestamp timestamp) {
                return timestamp == null ? null : timestamp.toLocalDateTime();
            }
        };

        Converter<LocalDateTime, Timestamp> localDateTimeTimestampConverter = new AbstractConverter<>() {
            @Override
            protected Timestamp convert(LocalDateTime localDateTime) {
                return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
            }
        };

//        Converter<VkPost, VkPostResponse> vkAggregateToResponse = new AbstractConverter<VkPost, VkPostResponse>() {
//            @Override
//            protected VkPostResponse convert(VkPost vkPost) {
//                if(Objects.isNull(vkPost)) return null;
//                return new VkPostResponse()
//                        .setUuid(vkPost.getUuid())
//                        .setVkPostId(vkPost.getVkPostBKey().getOriginalPostId())
//                        .setOwnerId(vkPost.getVkPostBKey().getOwnerId())
//                        .setText(vkPost.getFullPost().getText())
//                        .setSupplierUuid(vkPost.getSupplier().getUuid())
//                        .setPublishedAt(vkPost.getFullPost().getPublishedAt())
//                        .setImages(vkPost.getFullPost().getImagesLinkList().stream().map(picture -> picture.getUri().toString()).toList());
//            }
//        };

        modelMapper.addConverter(timestampLocalDateTimeConverter);
        modelMapper.addConverter(localDateTimeTimestampConverter);
//        modelMapper.addConverter(vkAggregateToResponse);

        return modelMapper;
    }
}
