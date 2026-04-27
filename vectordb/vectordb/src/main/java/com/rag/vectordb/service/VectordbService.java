package com.rag.vectordb.service;

import java.util.List;

public interface VectordbService {
    String chatTemplate(String message,String userId);
    void saveData(List<String> list);
}
