package com.project.ticketsense.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class WebClientConfig {

    @Bean
    public WebClient geminiWebClient(GeminiProperties properties){
        return WebClient.builder()
                .baseUrl(properties.baseUrl())
                .build();
    }
}
