# TicketSense

An AI-powered support/incident ticket assistant built with Spring Boot. 
Instead of engineers manually searching through past tickets to resolve 
recurring issues, TicketSense retrieves semantically similar past tickets 
and generates a grounded, cited explanation using an LLM — reducing 
repeated incident lookup time.

## Why this project exists

Support/ops teams repeatedly solve the same problems because past 
resolutions are buried in a database and keyword search doesn't capture 
meaning (e.g. "payment failing at night" won't match a ticket titled 
"nightly batch job timeout" even though they're related).

TicketSense solves this using Retrieval-Augmented Generation (RAG): 
it finds relevant past tickets by meaning (not keywords), feeds them 
to an LLM as context, and returns an answer grounded in real historical 
data — with citations back to the source tickets.

## Tech Stack

- **Java 21 / Spring Boot** — core backend
- **Spring WebFlux (WebClient)** — HTTP calls to LLM provider
- **Google Gemini API** — LLM for embeddings + response generation
- **PostgreSQL + pgvector** — relational data + vector similarity search *(planned)*
- **Redis** — caching repeated AI queries *(planned)*
- **Kafka** — async embedding pipeline for new tickets *(planned)*

## Features (build progress)

- [ ] Ticket CRUD (Spring Boot + PostgreSQL)
- [ ] Raw LLM API call via WebClient (no framework abstraction)
- [ ] RAG search using pgvector embeddings
- [ ] AI answer generation with citations to source tickets
- [ ] Confidence threshold → "no similar ticket found" fallback
- [ ] Kafka async embedding pipeline
- [ ] Redis cache for repeated questions
- [ ] LLM API retry + timeout + fallback handling

## Status

🚧 In active development — currently on: raw LLM API integration.



1. Clone the repo
2. Set the following environment variable (never commit this):
