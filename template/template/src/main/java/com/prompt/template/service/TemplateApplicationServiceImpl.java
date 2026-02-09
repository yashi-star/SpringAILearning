package com.prompt.template.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.*;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TemplateApplicationServiceImpl implements TemplateApplicationService {

    private final ChatClient chatClient;

    public TemplateApplicationServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }


// Prompt Template
    public String chat(String message) {
        Prompt p1 = new Prompt(message);
        String chat = "As an expert in coding .Now reply this question : {message}";
        var tutorial = chatClient.
                prompt()
                .user(u -> u
                        .text(chat)
                        .param("message", message))
                .call()
                .content();
        return tutorial;
    }


//TemplateRendering
    public String chatTemp() {
        PromptTemplate prompt= PromptTemplate.builder().template("What is {techname} ?  TELL ME  example of {EXAMPLENM}").build();

        String r=prompt.render(Map.of(
                "techname","java",
                "EXAMPLENM","java collection"
    ));
        Prompt p1=new Prompt(r);
        return this.chatClient.prompt(p1).call().content();
    }

    //role based
    public String chatTemplate() {
            var systemPrompt= SystemPromptTemplate.builder().template("You are a helpful coding assistant and a coding expert").build();

             var systemMsg=systemPrompt.createMessage();

            var userPrompt=PromptTemplate.builder().template("What is {techname} ?  TELL ME  example of {EXAMPLENM}").build();
            var userMsg=userPrompt.createMessage(Map.of("techname","java", "EXAMPLENM" , "Collections"));

            Prompt p1=new Prompt(systemMsg,userMsg);
                return this.chatClient.prompt(p1).call().content();
}

    //fluent api
    public String chatApi() {
        return this.chatClient
                .prompt()
                .system(s->s.text("You are a helpful coding assistant and a coding expert too"))
                .user(u->u.text("What is {techname} ? TELL ME  example of {EXAMPLENM}" )
                        .params(Map.of("techname","java", "EXAMPLENM" , "Collections")))
                .call()
                .content();
    }
}