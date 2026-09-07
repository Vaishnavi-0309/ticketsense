package com.project.ticketsense.repository;

import com.project.ticketsense.dto.SimilarTicketProjection;
import com.project.ticketsense.entity.TicketEmbedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketEmbeddingRepository extends JpaRepository<TicketEmbedding,String> {

    @Query(value= """
            SELECT te.ticket_id AS ticketId,
            te.embedding <=> CAST(:queryVector AS vector) AS distance
            FROM ticket_embedding te
            ORDER BY te.embedding <=> CAST(:queryVector AS vector)
            LIMIT :limit
            """,nativeQuery = true)
    List<SimilarTicketProjection> findNearestNeighbors(@Param("queryVector") String queryVector,
                                                       @Param("limit") int limit);
}
