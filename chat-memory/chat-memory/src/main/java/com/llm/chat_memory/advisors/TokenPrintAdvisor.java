package com.advisor.custom_advise.advisors;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

public class TokenPrintAdvisor implements CallAdvisor, StreamAdvisor{

    Logger logger = Logger.getLogger(TokenPrintAdvisor.class.getName());

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

        this.logger.info("my token print adviseCall is done");
        this.logger.info("Requests"+chatClientRequest.prompt().getContents());
        ChatClientResponse chatClientResponse=callAdvisorChain.nextCall(chatClientRequest);

        this.logger.info("this will give you a response");
        this.logger.info("Responses"+ chatClientResponse.chatResponse().getResult().getOutput().getText());
        this.logger.info("PromptToken  Consumed" + chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());
        this.logger.info("CompletionToken Consumed" + chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());
        this.logger.info("Total Token Consumed" + chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens());
        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        Flux<ChatClientResponse> chatClientResponseFlux=streamAdvisorChain.nextStream(chatClientRequest);
        return chatClientResponseFlux;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}