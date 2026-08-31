package com.project.ticketsense.dto;

import java.util.List;

public record GeminiResp(List<Candidate> candidates, UsageMetaData usageMetaData) {
}
