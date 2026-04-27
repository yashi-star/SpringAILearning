package com.chatmemory.jdbc_repo.controller;

import com.chatmemory.jdbc_repo.service.JdbcRepoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
class JdbcRepoController {

    private final JdbcRepoService promptService;

    public JdbcRepoController(JdbcRepoService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String message,@RequestHeader("userId") String userId){
        return ResponseEntity.ok(promptService.chatTemplate(message,userId));
    }
}
