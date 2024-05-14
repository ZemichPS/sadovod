package by.zemich.cataloguems.catalogueservice.infrastrucrure.config;

import by.zemich.cataloguems.catalogueservice.domain.commands.CreateProductCardCommand;
import by.zemich.shareddomain.events.VkPostCreatedEvent;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

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


        Converter<VkPostCreatedEvent, CreateProductCardCommand> vkPostCreatedEventToCreateProductCardCommandConverter = new AbstractConverter<VkPostCreatedEvent, CreateProductCardCommand>() {
            @Override
            protected CreateProductCardCommand convert(VkPostCreatedEvent event) {
                if(Objects.isNull(event)) return null;

                return CreateProductCardCommand.builder()
                        .postUuid(event.getUuid().uuid())
                        .supplierUuid(event.getSupplierUuid())
                        .publishedAt(event.getVkPostData().publishedAt())
                        .postText(event.getVkPostData().text())
                        .imagesLinkList(event.getVkPostData().imagesLinkList())
                        .postUri(event.getUri()).build();
            }
        };


        modelMapper.addConverter(vkPostCreatedEventToCreateProductCardCommandConverter);
        modelMapper.addConverter(timestampLocalDateTimeConverter);
        modelMapper.addConverter(localDateTimeTimestampConverter);

        return modelMapper;
    }
}
