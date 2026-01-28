package com.multimodel.best_practice.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig{

@Bean(name="openAiChatClient")
public ChatClient openAiChatClient(OpenAiChatModel chatModel){
    return ChatClient.builder(chatModel).build();
}
@Bean(name="ollamaChatClient")
    public ChatClient ollamaChatClient(OllamaChatModel chatModel){
        return ChatClient.builder(chatModel).build();
    }
}

