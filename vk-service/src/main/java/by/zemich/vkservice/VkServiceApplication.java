package by.zemich.vkservice;

import by.zemich.vkservice.infrastructure.config.properties.VkApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties({VkApiProperties.class})
@EnableDiscoveryClient
@EnableFeignClients
public class VkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkServiceApplication.class, args);
	}

}
