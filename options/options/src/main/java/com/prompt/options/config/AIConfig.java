package com.prompt.options.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {
//method 3
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.defaultSystem("You are  a coding expert ")
        .defaultOptions(OllamaChatOptions.builder()
                .model("codellama")
                .temperature(0.4)
                .build()).build();
    }

}
