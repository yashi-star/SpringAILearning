package com.spring.advisors.service;
import com.spring.advisors.AdvisorsApplication;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AdvisorsApplicationServiceImpl implements AdvisorsApplicationService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/user-prompt.st")
    private Resource userPrompt;
    @Value("classpath:/prompts/system-prompt.st")
    private Resource systemPrompt;


//    public AdvisorsApplicationServiceImpl(ChatClient.Builder chatClient) {
//        this.chatClient = chatClient.build();
//    }
    public AdvisorsApplicationServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public String chatTemplate(String message) {

                return this.chatClient
                        .prompt()
//                        .advisors(new SimpleLoggerAdvisor())
                        .system(s->s.text(systemPrompt))
                        .user(u->u.text(userPrompt).param("code",message))
                        .call()
                        .content();

}
}