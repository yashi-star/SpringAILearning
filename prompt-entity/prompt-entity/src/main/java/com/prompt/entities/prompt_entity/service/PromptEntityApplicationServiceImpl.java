package com.prompt.entities.prompt_entity.service;

import com.prompt.entities.prompt_entity.entity.Tutorial;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptEntityApplicationServiceImpl implements PromptEntityApplicationService {
    private final ChatClient chatClient;

    public PromptEntityApplicationServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }


//    public Tutorial chat(String message) {
//        Tutorial tut=chatClient
//                .prompt(message)
//                .call()
//                .entity(Tutorial.class);
//             return tut;
//    }


    //for list of generations
    public List<Tutorial> chat(String message) {
        List<Tutorial> tutorials=chatClient
                .prompt(message)
                .call()
                .entity(new ParameterizedTypeReference<List<Tutorial>>() {
                });
        return tutorials;
    }

}
