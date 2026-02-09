package com.prompt.resource.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prompt.resource.service.ResourceApplicationService;
@RestController
@RequestMapping("/")
class ResourceApplicationController {

    private final ResourceApplicationService promptService;

    public ResourceApplicationController(ResourceApplicationService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String message){
        return ResponseEntity.ok(promptService.chatTemplate());
    }
}
