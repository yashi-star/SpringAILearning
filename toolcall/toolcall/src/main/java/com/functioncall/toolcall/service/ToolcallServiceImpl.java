package com.functioncall.toolcall.service;

import com.functioncall.toolcall.tools.SimpleDataTimeTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;


@Service
public class ToolcallServiceImpl implements ToolcallService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ChatClient chatClient;


    public ToolcallServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public String getResponse(String userQuery) {

        return chatClient
                .prompt()
                .tools(new SimpleDataTimeTool())
                .user(userQuery)
                .call()
                .content();
    }




}