package com.multimodel.best_practice.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BestPracticeApplicationController {

    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;

    public BestPracticeApplicationController(OpenAiChatModel openAiChatModel, OllamaChatModel ollamaChatModel) {
        this.openAiChatClient = ChatClient.builder(openAiChatModel).build();
        this.ollamaChatClient = ChatClient.builder(ollamaChatModel).build();
    }


//    //best practice using aiconfig
//    public BestPracticeApplicationController(@Qualifier ("openAiChatClient") ChatClient openAiChatClient, @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
//        this.openAiChatClient = openAiChatClient;
//        this.ollamaChatClient = ollamaChatClient;
//    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String q) {
        String reply = ollamaChatClient.prompt(q).call().content();
        return ResponseEntity.ok(reply);
    }
}