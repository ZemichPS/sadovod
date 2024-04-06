package by.zemich.parserservice.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {


    @Bean
    MinioClient minioClient() {
        return MinioClient.builder()
                .credentials("miniouser", "miniopassword")
                .endpoint("http://127.0.0.1:9000")
                .build();
    }

}
