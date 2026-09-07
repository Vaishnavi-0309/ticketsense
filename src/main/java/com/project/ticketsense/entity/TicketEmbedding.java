package com.project.ticketsense.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ticket_embedding")
public class TicketEmbedding {
    @Id
    private String ticketId;

    @OneToOne
    @MapsId
    @JoinColumn(name="ticket_id")
    private Ticket ticket;

    @Column(columnDefinition = "vector(3072)")
    private float[] embedding;
}
