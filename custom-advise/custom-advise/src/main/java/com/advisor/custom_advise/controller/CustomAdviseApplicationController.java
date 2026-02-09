package com.advisor.custom_advise.controller;

import com.advisor.custom_advise.service.CustomAdviseApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/")
class CustomAdviseApplicationController {

    private final CustomAdviseApplicationService promptService;

    public CustomAdviseApplicationController(CustomAdviseApplicationService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String message){
        return ResponseEntity.ok(promptService.chatTemplate(message));
    }

    @GetMapping("/stream-chat")
    public ResponseEntity<Flux<String>> stream(@RequestParam("q") String message){
        return ResponseEntity.ok(promptService.streamChat(message));
    }
}
