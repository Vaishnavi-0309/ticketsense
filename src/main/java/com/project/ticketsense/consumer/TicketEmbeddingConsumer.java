package com.project.ticketsense.consumer;

import com.project.ticketsense.entity.Ticket;
import com.project.ticketsense.entity.TicketEmbedding;
import com.project.ticketsense.repository.TicketEmbeddingRepository;
import com.project.ticketsense.repository.TicketRepository;
import com.project.ticketsense.service.EmbeddingService;
import jakarta.transaction.Transactional;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.google.genai.text.GoogleGenAiTextEmbeddingModel;
import org.springframework.ai.google.genai.text.GoogleGenAiTextEmbeddingOptions;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketEmbeddingConsumer {
    private TicketRepository ticketRepository;
    private TicketEmbeddingRepository ticketEmbeddingRepository;
    private EmbeddingService embeddingService;

    public TicketEmbeddingConsumer(TicketRepository ticketRepository, TicketEmbeddingRepository ticketEmbeddingRepository,
                                   EmbeddingService embeddingService) {
        this.ticketRepository = ticketRepository;
        this.ticketEmbeddingRepository = ticketEmbeddingRepository;
        this.embeddingService = embeddingService;
    }

    @Transactional
    @KafkaListener(topics = "ticket-created",groupId = "ticketsense-embedding-group")
    public void consume(String ticketId){
        Ticket ticket=ticketRepository.findById(ticketId)
                .orElseThrow(()->new RuntimeException("Ticket not found: "+ticketId));


        float[] vector= embeddingService.embed(ticket.getDescription());
        TicketEmbedding embedding=new TicketEmbedding();
        embedding.setTicket(ticket);
        embedding.setEmbedding(vector);
        ticketEmbeddingRepository.save(embedding);

    }
}
