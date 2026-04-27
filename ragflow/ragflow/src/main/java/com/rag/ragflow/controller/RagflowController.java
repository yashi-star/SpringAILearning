package com.rag.ragflow.controller;

import com.rag.ragflow.service.RagflowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
class RagflowController {

    private final RagflowService promptService;

    public RagflowController(RagflowService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q",required = true) String message, @RequestHeader("userId") String userId){
        return ResponseEntity.ok(promptService.chatTemplate(message,userId));
    }
}
