package com.rag.ragflow.service;

import java.util.List;

public interface RagflowService {
    String chatTemplate(String message,String userId);
     void saveData(List<String> list);
}
