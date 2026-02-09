package com.llm.chat_memory.service;

import reactor.core.publisher.Flux;

public interface ChatMemoryService {
    String chatTemplate(String message);

}
