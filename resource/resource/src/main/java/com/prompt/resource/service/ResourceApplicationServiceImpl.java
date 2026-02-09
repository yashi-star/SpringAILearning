package com.prompt.resource.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ResourceApplicationServiceImpl implements ResourceApplicationService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/user-prompt.st")
    private Resource userPrompt;
    @Value("classpath:/prompts/system-prompt.st")
    private Resource systemPrompt;
    public ResourceApplicationServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }


    public String chatTemplate() {

                return this.chatClient
                        .prompt()
                        .system(s->s.text(systemPrompt))
                        .user(u->u.text(userPrompt).param("code","Java"))
                        .call()
                        .content();

}
}