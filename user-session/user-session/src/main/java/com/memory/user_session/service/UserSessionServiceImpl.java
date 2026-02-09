package com.memory.user_session.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserSessionServiceImpl implements UserSessionService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/user-prompt.st")
    private Resource userPrompt;
    @Value("classpath:/prompts/system-prompt.st")
    private Resource systemPrompt;


    public UserSessionServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public String chatTemplate(String message, String userId) {

                return this.chatClient
                        .prompt()
                        .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))
                        .system(s->s.text(systemPrompt))
                        .user(u->u.text(userPrompt).param("code",message))
                        .call()
                        .content();

}

}