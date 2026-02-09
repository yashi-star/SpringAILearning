package com.llm.chat_memory.controller;

import com.llm.chat_memory.service.ChatMemoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/")
class ChatMemoryController {

    private final ChatMemoryService promptService;

    public ChatMemoryController(ChatMemoryService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String message){
        return ResponseEntity.ok(promptService.chatTemplate(message));
    }
}
