package com.springai.prompting.prompt.controller;

import com.springai.prompting.prompt.service.PromptApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class PromptApplicationController{

    private final PromptApplicationService promptService;

    public PromptApplicationController(PromptApplicationService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String message){
        return ResponseEntity.ok(promptService.chat(message));
    }
}
