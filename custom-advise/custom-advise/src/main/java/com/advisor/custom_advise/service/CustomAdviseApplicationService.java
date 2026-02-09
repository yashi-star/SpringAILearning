package com.advisor.custom_advise.service;


import reactor.core.publisher.Flux;

public interface CustomAdviseApplicationService {
    String chatTemplate(String message);
    Flux<String> streamChat(String msg);
}
