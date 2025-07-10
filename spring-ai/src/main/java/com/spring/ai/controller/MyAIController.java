package com.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAIController {
	
	@Autowired
	private ChatClient chatClient;
	
	@GetMapping("/prompt")
	public String prompt(@RequestParam String promptMsg) {
		Prompt prompt = new Prompt(promptMsg);
		String promptResponse = chatClient.prompt(prompt).call().content();
		System.out.println("Prompt Response: " + promptResponse);
		return promptResponse;
	}
}
