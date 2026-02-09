package com.spring.advisors.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient  chatClient(ChatClient.Builder builder) {
            return builder
                    .defaultAdvisors(new SimpleLoggerAdvisor(),new SafeGuardAdvisor(List.of("machine")))
                    .defaultOptions(OllamaChatOptions.builder().model("codellama").temperature(0.7).build()).build();

    }
}
