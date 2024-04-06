package by.zemich.parserservice.config;

import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.core.model.UserDto;
import by.zemich.parserservice.dao.entity.SupplierEntity;
import by.zemich.parserservice.dao.entity.UserEntity;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.modelmapper.spring.SpringIntegration;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

        modelMapper.addConverter(timestampLocalDateTimeConverter);
        modelMapper.addConverter(localDateTimeTimestampConverter);

        return modelMapper;
    }
}
