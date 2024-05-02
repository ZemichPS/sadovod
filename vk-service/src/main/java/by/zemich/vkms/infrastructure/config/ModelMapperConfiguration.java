package by.zemich.vkms.infrastructure.config;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.interfaces.rest.dto.VkPostResponse;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
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

        Converter<VkPost, VkPostResponse> vkAggregateToResponse = new AbstractConverter<VkPost, VkPostResponse>() {
            @Override
            protected VkPostResponse convert(VkPost vkPost) {
                if(Objects.isNull(vkPost)) return null;
                return new VkPostResponse()
                        .setUuid(vkPost.getUuid())
                        .setVkPostId(vkPost.getVkPostId().getVkPostId())
                        .setOwnerId(vkPost.getVkPostId().getOwnerId())
                        .setText(vkPost.getFullPost().getText())
                        .setSupplierUuid(vkPost.getSupplier().getUuid())
                        .setPublishedAt(vkPost.getFullPost().getPublishedAt())
                        .setImages(vkPost.getFullPost().getImagesLinkList().stream().map(URI::toString).toList());
            }
        };

        modelMapper.addConverter(timestampLocalDateTimeConverter);
        modelMapper.addConverter(localDateTimeTimestampConverter);
        modelMapper.addConverter(vkAggregateToResponse);

        return modelMapper;
    }
}
