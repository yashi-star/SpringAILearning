package com.prompt.options.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.DefaultChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

@Service
public class OptionsApplicationServiceImpl implements OptionsApplicationService {

    private final ChatClient chatClient;

    //method 2
//    public OptionsApplicationServiceImpl(ChatClient.Builder chatClient) {
//        this.chatClient = chatClient.defaultOptions(OllamaChatOptions.builder()
//                .model("codellama")
//                .temperature(0.5)
//                .build()).build();
//    }


    //method 3 ai config
    public OptionsApplicationServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }



    public String chat(String message) {
        //method 1
//        Prompt p1 = new Prompt(
//                message,
//                OllamaChatOptions.builder()
//                        .model("codellama")
//                        .temperature(0.3)
//                        .build()
//        );
    Prompt p1=new Prompt(message);
            var tutorial=chatClient.prompt(p1).call().content();
            return tutorial;
    }

}