package com.project.ticketsense.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchResult {
    private boolean matchFound;
    private List<SimilarTicketProjection> matches;
    private String message;

    public SearchResult(List<SimilarTicketProjection> matches) {
        this.matchFound = true;
        this.matches = matches;
        this.message = "Similar Tickets Found";
    }

    public SearchResult(String message) {
        this.matchFound = true;
        this.matches = new ArrayList<>();
        this.message = message;
    }



}
