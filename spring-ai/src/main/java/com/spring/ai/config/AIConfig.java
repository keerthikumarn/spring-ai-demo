package com.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class AIConfig {

	@Value("${spring.ai.openai.api-key}")
	private String apiKey;

	@Value("${spring.ai.openai.api-base-url}")
	private String apiBaseUrl;

	@Value("${spring.ai.openai.chat.options.model}")
	private String model;

	/*@Bean
	public OpenAiApi openAiApi(@Value("${spring.ai.openai.api-key}") String apiKey) {
		return new OpenAiApi(apiBaseUrl, apiKey);
	}*/
	
	@Bean
    public OpenAiApi openAiApi() {
        System.out.println("🔑 Injected API Key: " + apiKey);
        System.out.println("🌍 Injected Base URL: " + apiBaseUrl);
        return new OpenAiApi(apiBaseUrl, apiKey);
    }

	@Bean
	public OpenAiChatModel openAiChatModel(OpenAiApi openAiApi) {
		OpenAiChatOptions options = OpenAiChatOptions.builder().withModel(model).build();
		return new OpenAiChatModel(openAiApi, options);
	}

	@Bean
	public ChatClient chatClient(OpenAiChatModel chatModel) {
		return ChatClient.builder(chatModel).build();
	}

	@PostConstruct
	public void logConfig() {
		System.out.println("API Key: " + apiKey);
		System.out.println("Base URL: " + apiBaseUrl);
		System.out.println("Model: " + model);
	}
}
