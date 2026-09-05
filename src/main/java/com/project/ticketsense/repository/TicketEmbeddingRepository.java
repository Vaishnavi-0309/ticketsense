package com.project.ticketsense.repository;

import com.project.ticketsense.entity.TicketEmbedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketEmbeddingRepository extends JpaRepository<TicketEmbedding,String> {
}
