package com.spring.advisors.controller;

import com.spring.advisors.service.AdvisorsApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class AdvisorsApplicationController {

    private final AdvisorsApplicationService promptService;

    public AdvisorsApplicationController(AdvisorsApplicationService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String message){
        return ResponseEntity.ok(promptService.chatTemplate(message));
    }
}
