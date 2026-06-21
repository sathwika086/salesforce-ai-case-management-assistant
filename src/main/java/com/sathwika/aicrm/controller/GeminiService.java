package com.sathwika.aicrm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sathwika.aicrm.entity.CaseAnalysis;
import com.sathwika.aicrm.repository.CaseAnalysisRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import com.sathwika.aicrm.service.SalesforceService;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;
    private final CaseAnalysisRepository caseAnalysisRepository;
private final SalesforceService salesforceService;

public GeminiService(
        CaseAnalysisRepository caseAnalysisRepository,
        SalesforceService salesforceService) {

    this.caseAnalysisRepository = caseAnalysisRepository;
    this.salesforceService = salesforceService;
}

    public String analyzeCase(
        String description,
        String customerEmail){

        WebClient webClient = WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();

        String requestBody = """
        {
          "contents": [{
            "parts": [{
              "text": "Analyze the customer support case below and return ONLY valid JSON. Do not add explanations, markdown, or code blocks. Use this format: {\\\"category\\\":\\\"Billing\\\",\\\"priority\\\":\\\"High\\\",\\\"sentiment\\\":\\\"Negative\\\",\\\"summary\\\":\\\"Short summary here\\\",\\\"suggestedResolution\\\":\\\"Recommended action here\\\"}. Customer Case: %s"
            }]
          }]
        }
        """.formatted(description);

        try {

            String response = webClient.post()
                    .uri("/v1beta/models/gemini-2.5-flash-lite:generateContent?key=" + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .onStatus(
                            status -> status.isError(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .map(RuntimeException::new))
                    .bodyToMono(String.class)
                    .block();

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response);

            String aiText = root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            aiText = aiText
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            JsonNode analysis = mapper.readTree(aiText);

            CaseAnalysis caseAnalysis = new CaseAnalysis();

            caseAnalysis.setCategory(
                    analysis.path("category").asText());

            caseAnalysis.setPriority(
                    analysis.path("priority").asText());

            caseAnalysis.setSentiment(
                    analysis.path("sentiment").asText());

            caseAnalysis.setSummary(
                    analysis.path("summary").asText());

            caseAnalysis.setSuggestedResolution(
                    analysis.path("suggestedResolution").asText());

            caseAnalysis.setCustomerDescription(
                    description);
            caseAnalysis.setCustomerEmail(
        customerEmail);

            caseAnalysis.setStatus(
                    "Open");

            caseAnalysis.setAssignedTo(
                    "Unassigned");

            caseAnalysis.setCreatedAt(
                    LocalDateTime.now());

            CaseAnalysis savedCase =
        caseAnalysisRepository.save(caseAnalysis);

salesforceService.createCaseFromDb(savedCase.getId());

            String formattedOutput = """
                    AI Case Analysis

                    Category: %s
                    Priority: %s
                    Sentiment: %s

                    Summary:
                    %s

                    Suggested Resolution:
                    %s
                    """.formatted(
                    analysis.path("category").asText(),
                    analysis.path("priority").asText(),
                    analysis.path("sentiment").asText(),
                    analysis.path("summary").asText(),
                    analysis.path("suggestedResolution").asText()
            );

            return formattedOutput;

        } catch (Exception e) {
            return "Error calling Gemini: " + e.getMessage();
        }
    }
}