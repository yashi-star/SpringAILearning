package com.spring.ai.first_project.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FirstProjectController {

    private ChatClient chatClient;

    public FirstProjectController(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String query) {
        String reply=chatClient.prompt(query).call().content();
        return new ResponseEntity<>(reply,HttpStatus.OK);
    }
}
