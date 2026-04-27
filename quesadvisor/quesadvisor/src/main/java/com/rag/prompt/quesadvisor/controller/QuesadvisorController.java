package com.rag.prompt.quesadvisor.controller;

import com.rag.prompt.quesadvisor.service.QuesadvisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
class QuesadvisorController {

    private final QuesadvisorService promptService;

    public QuesadvisorController(QuesadvisorService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q",required = true) String message, @RequestHeader("userId") String userId){
        return ResponseEntity.ok(promptService.chatTemplate(message,userId));
    }
}
