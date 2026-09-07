package com.project.ticketsense.service;

import com.project.ticketsense.dto.SearchResult;
import com.project.ticketsense.dto.SimilarTicketProjection;
import com.project.ticketsense.repository.TicketEmbeddingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketSearchService {

    private static final double CONFIDENCE_THRESHOLD=0.3;

    private EmbeddingService embeddingService;
    private TicketEmbeddingRepository ticketEmbeddingRepository;

    public TicketSearchService(EmbeddingService embeddingService,
                               TicketEmbeddingRepository ticketEmbeddingRepository) {
        this.embeddingService = embeddingService;
        this.ticketEmbeddingRepository = ticketEmbeddingRepository;
    }


    public SearchResult searchTickets(String questionText){
        List<SimilarTicketProjection> results=findSimilarTicket(questionText);

        if (results == null || results.isEmpty()) {
            return new SearchResult("No tickets exist yet to compare against.");
        }

        List<SimilarTicketProjection> goodMatches = new ArrayList<>();

        for (SimilarTicketProjection result : results) {
            if (result.getDistance() <= CONFIDENCE_THRESHOLD) {
                goodMatches.add(result);
            }
        }

        if (goodMatches.isEmpty()) {
            return new SearchResult("No similar ticket found.");
        } else {
            return new SearchResult(goodMatches);
        }
    }
    public List<SimilarTicketProjection> findSimilarTicket(String questionText){
        float[] vector=embeddingService.embed(questionText);
        String vectorString=toVectorString(vector);
        return ticketEmbeddingRepository.findNearestNeighbors(vectorString,5);
    }

    public String toVectorString(float[] vector) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < vector.length; i++) {
            String val= String.valueOf(vector[i]);
            sb.append(val);
            if(i!=vector.length-1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
