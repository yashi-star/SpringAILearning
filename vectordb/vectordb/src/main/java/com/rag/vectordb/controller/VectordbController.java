trtrtr5package com.rag.vectordb.controller;

import com.rag.vectordb.service.VectordbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/")
class VectordbController {

    private final VectordbService promptService;

    public VectordbController(VectordbService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q",required = true) String message, @RequestHeader("userId") String userId){
        return ResponseEntity.ok(promptService.chatTemplate(message,userId));
    }
}
