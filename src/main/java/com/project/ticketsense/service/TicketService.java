package com.project.ticketsense.service;

import com.project.ticketsense.entity.Ticket;
import com.project.ticketsense.repository.TicketRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private static final String TOPIC="ticket-created";
    private TicketRepository ticketRepository;
    private KafkaTemplate<String,String> kafkaTemplate;

    public TicketService(TicketRepository ticketRepository,
                         KafkaTemplate<String,String> kafkaTemplate) {
        this.ticketRepository = ticketRepository;
        this.kafkaTemplate=kafkaTemplate;
    }

    public Ticket createTicket(Ticket ticket){
        Ticket saved=ticketRepository.save(ticket);
        kafkaTemplate.send(TOPIC,saved.getId());
        return  saved;
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicket(String id){
        return ticketRepository.findById(id);
    }

    public Optional<Ticket> updateTicket(String id, Ticket updated){
        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setCategory(updated.getCategory());
                    ticket.setDescription(updated.getDescription());
                    ticket.setResolvedStatus(updated.isResolvedStatus());
                    ticket.setSolution(updated.getSolution());
                    return ticketRepository.save(ticket);
                });
    }

    public boolean deleteTicket(String id){
        if(!ticketRepository.existsById(id)){
            return false;
        }
        ticketRepository.deleteById(id);
        return true;
    }
}
