package com.project.ticketsense.service;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.google.genai.text.GoogleGenAiTextEmbeddingOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public float[] embed(String text){
        GoogleGenAiTextEmbeddingOptions options= GoogleGenAiTextEmbeddingOptions.builder()
                .model("gemini-embedding-001")
                .build();

        EmbeddingResponse response=embeddingModel.call(
                new EmbeddingRequest(List.of(text),options)
        );

        float[] vector=response.getResults().getFirst().getOutput();
        System.out.println(vector.length);
        return vector;
    }
}
