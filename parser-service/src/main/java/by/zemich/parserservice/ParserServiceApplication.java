package by.zemich.parserservice;


import by.zemich.parserservice.config.property.GTranslateProperty;
import by.zemich.parserservice.config.property.ViberProperty;
import by.zemich.parserservice.config.property.VkApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableConfigurationProperties({VkApiProperties.class, ViberProperty.class, GTranslateProperty.class})
@EnableJpaRepositories
@EnableFeignClients
@EnableTransactionManagement
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication()
public class ParserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParserServiceApplication.class, args);

    }

}

