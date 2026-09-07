package com.project.ticketsense.controller;

import com.google.api.client.json.Json;
import com.project.ticketsense.dto.SearchRequest;
import com.project.ticketsense.dto.SearchResult;
import com.project.ticketsense.dto.SimilarTicketProjection;
import com.project.ticketsense.entity.Ticket;
import com.project.ticketsense.service.TicketSearchService;
import com.project.ticketsense.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;
    private TicketSearchService ticketSearchService;

    public TicketController(TicketService ticketService,TicketSearchService ticketSearchService) {
        this.ticketService = ticketService;
        this.ticketSearchService=ticketSearchService;
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket){
        return ticketService.createTicket(ticket);
    }

    @GetMapping
    public List<Ticket> getAllTickets( ){
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable String id) {
        return ticketService.getTicket(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable String id, @RequestBody Ticket updated) {
        return ticketService.updateTicket(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
        return ticketService.deleteTicket(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/search")
    public SearchResult findSimilarTickets(@RequestBody SearchRequest request) {
        return ticketSearchService.searchTickets(request.getQuestion());
    }
}
