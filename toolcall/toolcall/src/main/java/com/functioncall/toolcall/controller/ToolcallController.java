package com.functioncall.toolcall.controller;

import com.functioncall.toolcall.service.ToolcallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
class ToolcallController {

    private final ToolcallService promptService;

    public ToolcallController(ToolcallService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> getResponse(@RequestParam(value = "q") String userQuery){
        return ResponseEntity.ok(promptService.getResponse(userQuery));
    }
}

