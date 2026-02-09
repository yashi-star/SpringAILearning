package com.memory.user_session.controller;

import com.memory.user_session.service.UserSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
class UserSessionController {

    private final UserSessionService promptService;

    public UserSessionController(UserSessionService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String message,@RequestHeader("userId") String userId){
        return ResponseEntity.ok(promptService.chatTemplate(message, userId));
    }
}
