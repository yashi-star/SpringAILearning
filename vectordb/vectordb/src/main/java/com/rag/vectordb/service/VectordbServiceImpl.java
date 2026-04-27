package com.rag.vectordb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VectordbServiceImpl implements VectordbService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ChatClient chatClient;


    @Autowired
    private VectorStore vectorstore;  //you can also do constructor injection

    @Value("classpath:/prompts/user-prompt.st")
    private Resource userPrompt;
    @Value("classpath:/prompts/system-prompt.st")
    private Resource systemPrompt;


    public VectordbServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public String chatTemplate(String message,String userId) {


        //load data from vector database
        SearchRequest searchRequest = SearchRequest
                .builder()
                .topK(2)
                .similarityThreshold(0.7)  // 1 means exact match 0 means random
                .query(message)
                .build();

        List<Document> documents=this.vectorstore.similaritySearch(searchRequest);
        List<String>doclist=documents.stream().map(Document::getText).toList();
        String context=String.join(", ",doclist);
        logger.info("context data {} :  "+context);

        //similar result user query
        // passs in context
                return this.chatClient
                        .prompt()
                        .system(s->s.text(this.systemPrompt).param("documents",context))
                        .user(u->u.text(userPrompt).param("message",message))
                        .call()
                        .content();
    }

    public void saveData(List<String> list) {
       List<Document>docList= list.stream().map(Document::new).toList();
       this.vectorstore.add(docList);
    }


}