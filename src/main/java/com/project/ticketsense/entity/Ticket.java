package com.project.ticketsense.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="ticket_id")
    private String id;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private boolean resolvedStatus;

    @Column(columnDefinition = "TEXT")
    private String solution;

}
