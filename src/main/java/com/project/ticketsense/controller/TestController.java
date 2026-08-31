package com.project.ticketsense.controller;

import com.project.ticketsense.service.AiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final AiClient aiClient;

    public TestController(AiClient aiClient) {
        this.aiClient = aiClient;
    }

    @GetMapping("/test-ai")
    public String testAi(@RequestParam String prompt) {
        return aiClient.getPromptResp(prompt);
    }
}