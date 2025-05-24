package com.spring.ai.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;

@Configuration
public class MyAIConfig {

	@Bean
	public ChatClient chatClient(ChatModel chatModel) {
		return ChatClient.create(chatModel);
	}
}
