package com.llm.chat_memory.config;

import com.advisor.custom_advise.advisors.TokenPrintAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiConfig {

    Logger logger = LoggerFactory.getLogger(AiConfig.class);

    @Bean
    public ChatClient  chatClient(ChatClient.Builder builder , ChatMemory chatMemory) {

        this.logger.info("Chatmemory " +chatMemory.getClass().getName());
        MessageChatMemoryAdvisor messageChatMemoryAdvisor=MessageChatMemoryAdvisor.builder(chatMemory).build();

            return builder
                    .defaultAdvisors(messageChatMemoryAdvisor,new TokenPrintAdvisor(),new SafeGuardAdvisor(List.of("machine")))
                    .defaultOptions(OllamaChatOptions.builder().model("codellama").temperature(0.7).build()).build();

    }
}
