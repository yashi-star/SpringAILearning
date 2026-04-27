package com.padtovector.etl.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTransformerServiceImpl implements DataTransformerService{
   public  List<Document> transform(List<Document> documents) {
       var splitter=TokenTextSplitter.builder()
               .withChunkSize(300)
               .withKeepSeparator(true)
               .withMinChunkLengthToEmbed(400)
               .withMaxNumChunks(5000)
               .build();
       return splitter.transform(documents);

    }
}
