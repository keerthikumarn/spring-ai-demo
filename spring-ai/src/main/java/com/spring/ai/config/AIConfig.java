package com.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class AIConfig {

	@Value("${spring.ai.openai.api-key}")
	private String apiKey;

	@Value("${spring.ai.openai.base-url}")
	private String apiBaseUrl;

	@Value("${spring.ai.openai.chat.model}")
	private String model;

	@Autowired
	private ChatModel chatModel;

	@Bean
	public OpenAiApi openAiApi() {
		return new OpenAiApi(apiBaseUrl, apiKey);
	}

	@Bean
	public ChatClient chatClient() {
		return ChatClient.builder(chatModel).build();
	}

	@Bean
	public OpenAiChatModel openAiChatModel(OpenAiApi openAiApi) {
		OpenAiChatOptions options = OpenAiChatOptions.builder().withModel(model).build();
		return new OpenAiChatModel(openAiApi, options);
	}

	@PostConstruct
	public void logConfig() {
		System.out.println("API Key: " + apiKey);
		System.out.println("Base URL: " + apiBaseUrl);
		System.out.println("Model: " + model);
	}

}
