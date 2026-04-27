package com.rag.prompt.quesadvisor.service;

import java.util.List;

public interface QuesadvisorService {
    String chatTemplate(String message,String userId);
    void saveData(List<String> list);
}
