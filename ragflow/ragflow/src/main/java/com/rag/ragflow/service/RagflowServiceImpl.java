package com.rag.ragflow.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RagflowServiceImpl implements RagflowService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ChatClient chatClient;


    @Autowired
    private VectorStore vectorstore;  //you can also do constructor injection

    @Value("classpath:/prompts/user-prompt.st")
    private Resource userPrompt;
    @Value("classpath:/prompts/system-prompt.st")
    private Resource systemPrompt;


    public RagflowServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public String chatTemplate(String message,String userId) {

        var advisor= RetrievalAugmentationAdvisor.builder()
                .documentRetriever(VectorStoreDocumentRetriever.builder()
                        .vectorStore(vectorstore)
                        .topK(3)
                        .similarityThreshold(0.6)
                        .build())
                .queryAugmenter(ContextualQueryAugmenter.builder()
                        .allowEmptyContext(true)
                        .build())
                .build();

                return this.chatClient
                        .prompt()
                        .advisors(advisor)
                        .user(u->u.text(userPrompt).param("message",message))
                        .call()
                        .content();
    }

    public void saveData(List<String> list) {
       List<Document>docList= list.stream().map(Document::new).toList();
       this.vectorstore.add(docList);
    }


}