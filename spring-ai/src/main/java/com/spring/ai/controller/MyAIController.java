package com.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAIController {
	
	@Autowired
	ChatClient chatClient;

	@GetMapping("/prompt")
	public String prompt(@RequestParam String promptMsg) {
		System.out.println("Processed input: " + promptMsg);
		return chatClient.prompt(promptMsg).call().content().toString();
	}
}
