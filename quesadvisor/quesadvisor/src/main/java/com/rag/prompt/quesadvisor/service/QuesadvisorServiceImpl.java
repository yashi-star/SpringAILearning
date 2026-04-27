package com.rag.prompt.quesadvisor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class QuesadvisorServiceImpl implements QuesadvisorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ChatClient chatClient;


    @Autowired
    private VectorStore vectorstore;  //you can also do constructor injection

    @Value("classpath:/prompts/user-prompt.st")
    private Resource userPrompt;
    @Value("classpath:/prompts/system-prompt.st")
    private Resource systemPrompt;


    public QuesadvisorServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public String chatTemplate(String message,String userId) {

                return this.chatClient
                        .prompt()
//                        .advisors(QuestionAnswerAdvisor.builder(vectorstore).build())
                        .advisors(QuestionAnswerAdvisor.builder(vectorstore)
                                .searchRequest(SearchRequest.builder().
                                        topK(3).
                                        similarityThreshold(0.5).
                                        build())
                                .build())
                        .user(u->u.text(userPrompt).param("message",message))
                        .call()
                        .content();
    }

    public void saveData(List<String> list) {
       List<Document>docList= list.stream().map(Document::new).toList();
       this.vectorstore.add(docList);
    }


}