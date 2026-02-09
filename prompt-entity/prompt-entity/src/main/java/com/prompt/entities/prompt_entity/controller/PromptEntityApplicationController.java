package com.prompt.entities.prompt_entity.controller;

import com.prompt.entities.prompt_entity.entity.Tutorial;
import com.prompt.entities.prompt_entity.service.PromptEntityApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PromptEntityApplicationController {

    private final PromptEntityApplicationService promptService;

    public PromptEntityApplicationController(PromptEntityApplicationService promptService) {
        this.promptService = promptService;
    }

//    @GetMapping("/chat")
//    public ResponseEntity<Tutorial> chat(@RequestParam("q") String message){
//        return ResponseEntity.ok(promptService.chat(message));
//    }

    //list of tutorials
        @GetMapping("/chat")
    public ResponseEntity<List<Tutorial>> chat(@RequestParam("q") String message){
        return ResponseEntity.ok(promptService.chat(message));
    }



}
