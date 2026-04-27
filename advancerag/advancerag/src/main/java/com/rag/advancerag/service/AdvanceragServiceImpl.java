package com.rag.advancerag.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.join.ConcatenationDocumentJoiner;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdvanceragServiceImpl implements AdvanceragService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ChatClient chatClient;

    @Autowired
    private VectorStore vectorstore;  //you can also do constructor injection

    @Value("classpath:/prompts/user-prompt.st")
    private Resource userPrompt;
    @Value("classpath:/prompts/system-prompt.st")
    private Resource systemPrompt;

    public AdvanceragServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public String getResponse(String userQuery) {

        //pre retrival
        var advisor=RetrievalAugmentationAdvisor.builder()
                .queryTransformers(RewriteQueryTransformer.builder()
                        .chatClientBuilder(chatClient.mutate().clone())
                        .build(),
                        TranslationQueryTransformer.builder()
                                .chatClientBuilder(chatClient.mutate().clone())
                                .targetLanguage("hindi").build())

                .queryExpander(MultiQueryExpander.builder().
                        chatClientBuilder(chatClient.mutate().clone())
                        .numberOfQueries(3)
                        .build())

         //retrieval
                .documentRetriever(VectorStoreDocumentRetriever.builder()
                        .vectorStore(vectorstore)
                        .topK(2)
                        .similarityThreshold(0.3)
                        .build())

         //post retrieval
                .documentJoiner(new ConcatenationDocumentJoiner())
               // .documentPostProcessors()
        //generation
                .queryAugmenter(ContextualQueryAugmenter.builder().build())

                .build();

        //actual call to llm
        return chatClient
                .prompt()
                .advisors(advisor)
                .user(userQuery)
                .call()
                .content();
    }

    public void saveData(List<String> list) {
       List<Document>docList= list.stream().map(Document::new).toList();
       this.vectorstore.add(docList);
    }


}