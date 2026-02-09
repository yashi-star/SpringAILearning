package com.llm.chat_memory.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatMemoryServiceImpl implements ChatMemoryService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/user-prompt.st")
    private Resource userPrompt;
    @Value("classpath:/prompts/system-prompt.st")
    private Resource systemPrompt;


    public ChatMemoryServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public String chatTemplate(String message) {

                return this.chatClient
                        .prompt()
                        .system(s->s.text(systemPrompt))
                        .user(u->u.text(userPrompt).param("code",message))
                        .call()
                        .content();

}




}