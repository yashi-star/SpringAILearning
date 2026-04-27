package com.functioncall.toolcall.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient  chatClient(ChatClient.Builder builder) {
            return builder
                    .defaultAdvisors(new SimpleLoggerAdvisor())
                    .defaultOptions(OllamaChatOptions.builder()
                            .model("llama3.1")
                            .temperature(0.7)
                            .build())
                    .build();

    }
}
