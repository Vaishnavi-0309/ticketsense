package com.project.ticketsense.consumer;

import com.project.ticketsense.entity.Ticket;
import com.project.ticketsense.entity.TicketEmbedding;
import com.project.ticketsense.repository.TicketEmbeddingRepository;
import com.project.ticketsense.repository.TicketRepository;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TicketEmbeddingConsumer {
    private TicketRepository ticketRepository;
    private TicketEmbeddingRepository ticketEmbeddingRepository;
    private EmbeddingModel embeddingModel;

    public TicketEmbeddingConsumer(TicketRepository ticketRepository, TicketEmbeddingRepository ticketEmbeddingRepository,
                                   EmbeddingModel embeddingModel) {
        this.ticketRepository = ticketRepository;
        this.ticketEmbeddingRepository = ticketEmbeddingRepository;
        this.embeddingModel = embeddingModel;
    }

    @KafkaListener(topics = "ticket-created",groupId = "ticketsense-embedding-group")
    public void consume(String ticketId){
        Ticket ticket=ticketRepository.findById(ticketId)
                .orElseThrow(()->new RuntimeException("Ticket not found: "+ticketId));
        float[] vector=embeddingModel.embed(ticket.getDescription());
        System.out.println(vector.length);

        TicketEmbedding embedding=new TicketEmbedding();
        embedding.setTicket(ticket);
        embedding.setEmbedding(vector);

        ticketEmbeddingRepository.save(embedding);

    }
}
