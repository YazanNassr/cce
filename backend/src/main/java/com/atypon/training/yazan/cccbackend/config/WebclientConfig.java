package com.atypon.training.yazan.cccbackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {
    @Bean
    public WebClient dockerWebClient(
            @Value("${docker.remote.api.url}")
            String dockerServerBaseUrl
    ) {

        return WebClient.builder()
                .baseUrl(dockerServerBaseUrl)
                .build();
    }
}
