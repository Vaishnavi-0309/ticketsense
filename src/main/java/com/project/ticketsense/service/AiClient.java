package com.project.ticketsense.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AiClient {

    private final ChatClient chatClient;

    public AiClient(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    // The chain is: .prompt(prompt) -> .call() -> .content()
    public String getPromptResp(String prompt){
        return chatClient.prompt(prompt).call().content();
    }



















//    public AiClient(GeminiProperties properties, WebClient webClient, ChatClient chatClient) {
//        this.properties = properties;
//        this.webClient = webClient;
//        this.chatClient = chatClient;
//    }
//
//    public String getAiResponse(String prompt){
//       GeminiReq req =new GeminiReq(
//               List.of(new Content(List.of(new Part(prompt)),null))
//       );
//
//        GeminiResp resp=webClient.post()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/models/{model}:generateContent")
//                        .queryParam("key",properties.apiKey())
//                        .build(properties.model()))
//                .bodyValue(req)
//                .retrieve()
//                .bodyToMono(GeminiResp.class)
//                .block();
//
//        assert resp != null;
//        return resp.candidates().getFirst().content().parts().getFirst().text();
//
//    }
}
