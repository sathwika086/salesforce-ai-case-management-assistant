package com.sathwika.aicrm.service;

import com.sathwika.aicrm.entity.CaseAnalysis;
import com.sathwika.aicrm.repository.CaseAnalysisRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SalesforceService {

    @Value("${salesforce.client.id}")
    private String clientId;

    @Value("${salesforce.client.secret}")
    private String clientSecret;

    @Value("${salesforce.login.url}")
    private String loginUrl;

    @Value("${salesforce.instance.url}")
    private String instanceUrl;

    private final WebClient webClient = WebClient.builder().build();
    private final CaseAnalysisRepository caseAnalysisRepository;

    public SalesforceService(CaseAnalysisRepository caseAnalysisRepository) {
        this.caseAnalysisRepository = caseAnalysisRepository;
    }

    public String createCaseFromDb(Long id) {

    CaseAnalysis caseAnalysis = caseAnalysisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Case not found"));

    if (caseAnalysis.getSalesforceCaseId() != null &&
        !caseAnalysis.getSalesforceCaseId().isEmpty()) {

        return "Already synced to Salesforce: "
                + caseAnalysis.getSalesforceCaseId();
    }
        try {

            String tokenResponse = webClient.post()
                    .uri(loginUrl + "/services/oauth2/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .bodyValue(
                            "grant_type=client_credentials" +
                                    "&client_id=" + clientId +
                                    "&client_secret=" + clientSecret
                    )
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            String accessToken = tokenResponse
                    .split("\"access_token\":\"")[1]
                    .split("\"")[0];

            String caseJson = String.format("""
                {
                  "Subject":"%s - %s",
                  "Description":"%s",
                  "Status":"New"
                }
                """,
                    caseAnalysis.getCategory(),
                    caseAnalysis.getPriority(),
                    caseAnalysis.getCustomerDescription()
            );

            String response = webClient.post()
        .uri(instanceUrl + "/services/data/v64.0/sobjects/Case")
        .header("Authorization", "Bearer " + accessToken)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(caseJson)
        .retrieve()
        .bodyToMono(String.class)
        .block();

String salesforceId = response
        .split("\"id\":\"")[1]
        .split("\"")[0];

caseAnalysis.setSalesforceCaseId(salesforceId);
caseAnalysis.setSalesforceSyncStatus("SYNCED");

caseAnalysisRepository.save(caseAnalysis);

return response;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}