package com.rag.advancerag.controller;
import com.rag.advancerag.service.AdvanceragService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
class AdvanceragController {

    private final AdvanceragService promptService;

    public AdvanceragController(AdvanceragService promptService) {
        this.promptService = promptService;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> getResponse(@RequestParam(value = "q") String userQuery){
        return ResponseEntity.ok(promptService.getResponse(userQuery));
    }
}

