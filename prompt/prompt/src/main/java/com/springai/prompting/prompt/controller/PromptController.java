package com.springai.prompting.prompt.controller;

@RestController
@Requestmapping("/")
class PromptController{

    private final ChatClient chatClient;

    public PromptController(ChatClient.Builder chatClientBuilder) {
        this.chatClient=chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam ("q") String message){
        String response= this.chatClient.prompt(message).call().content();
        return ResponseEntity.ok(response);
    }
}