package by.zemich.cataloguems.catalogueservice;

import by.zemich.cataloguems.catalogueservice.application.internal.outboundservices.alc.ExternalAiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients(clients = ExternalAIFeignClient.class)
@EnableFeignClients
public class CatalogueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogueServiceApplication.class, args);
	}

}
