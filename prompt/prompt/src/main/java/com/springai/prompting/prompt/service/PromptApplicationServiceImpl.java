package com.springai.prompting.prompt.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class PromptApplicationServiceImpl implements PromptApplicationService {

    private final ChatClient chatClient;

    public PromptApplicationServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }


    public String chat(String message) {

        //1st way
//        String response = chatClient.prompt("what is cricket?").call().content();

        //2nd way
//       String prompt="Tell me about virat kohli";
//        String response=chatClient.prompt(prompt).call().content();

        //3rd way
//    String prompt="Tell me about virat kohli";
//    String response=chatClient.prompt().user(prompt).system("As an expert in cricket").call().content();

        //4th way
//        String prompt="Tell me about virat kohli";
//        Prompt p1=new Prompt(prompt);
//        String response=chatClient.prompt(p1).call().content();

        //for detail response
        //String prompt="Tell me about virat kohli";
        //Prompt p1=new Prompt(prompt);
        var content=chatClient
                .prompt(message)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();

        System.out.println(content);

        return content;
    }

}