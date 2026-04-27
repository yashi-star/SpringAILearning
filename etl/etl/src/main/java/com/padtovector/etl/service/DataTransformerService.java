package com.padtovector.etl.service;

import org.springframework.ai.document.Document;

import java.util.List;

public interface DataTransformerService {
    List<Document> transform(List<Document> documents);
}
