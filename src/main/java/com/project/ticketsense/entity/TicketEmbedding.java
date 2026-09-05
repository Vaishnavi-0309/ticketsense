package com.project.ticketsense.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TicketEmbedding {
    @Id
    private String ticketId;

    @OneToOne
    @MapsId
    @JoinColumn(name="ticker_id")
    private Ticket ticket;

    @Column(columnDefinition = "vector(1536)")
    private float[] embedding;
}
