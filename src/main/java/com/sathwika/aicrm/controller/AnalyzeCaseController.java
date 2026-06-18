package com.sathwika.aicrm.controller;

import com.sathwika.aicrm.entity.CaseAnalysis;
import com.sathwika.aicrm.entity.CaseNote;
import com.sathwika.aicrm.repository.CaseAnalysisRepository;
import com.sathwika.aicrm.repository.CaseNoteRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnalyzeCaseController {

    private final GeminiService geminiService;
    private final CaseAnalysisRepository caseAnalysisRepository;
    private final CaseNoteRepository caseNoteRepository;

    public AnalyzeCaseController(
            GeminiService geminiService,
            CaseAnalysisRepository caseAnalysisRepository,
            CaseNoteRepository caseNoteRepository) {

        this.geminiService = geminiService;
        this.caseAnalysisRepository = caseAnalysisRepository;
        this.caseNoteRepository = caseNoteRepository;
    }

    @GetMapping("/analyze-case")
    public String analyzeCase(
            @RequestParam String description) {

        return geminiService.analyzeCase(description);
    }

    @GetMapping("/cases")
    public List<CaseAnalysis> getAllCases() {

        return caseAnalysisRepository.findAll();
    }

    @GetMapping("/cases/{id}")
    public CaseAnalysis getCaseById(
            @PathVariable Long id) {

        return caseAnalysisRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Case not found"));
    }

    @PutMapping("/cases/{id}/status")
    public CaseAnalysis updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        CaseAnalysis caseAnalysis =
                caseAnalysisRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Case not found"));

        caseAnalysis.setStatus(status);

        return caseAnalysisRepository.save(caseAnalysis);
    }

    @PutMapping("/cases/{id}/assign")
    public CaseAnalysis assignCase(
            @PathVariable Long id,
            @RequestParam String assignedTo) {

        CaseAnalysis caseAnalysis =
                caseAnalysisRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Case not found"));

        caseAnalysis.setAssignedTo(assignedTo);

        return caseAnalysisRepository.save(caseAnalysis);
    }

    @PostMapping("/cases/{id}/notes")
    public CaseNote addNote(
            @PathVariable Long id,
            @RequestBody NoteRequest request) {

        CaseNote note = new CaseNote();

        note.setCaseId(id);
        note.setNote(request.getNote());
        note.setCreatedAt(LocalDateTime.now());

        return caseNoteRepository.save(note);
    }

    @GetMapping("/cases/{id}/notes")
    public List<CaseNote> getNotes(
            @PathVariable Long id) {

        return caseNoteRepository
                .findByCaseIdOrderByCreatedAtDesc(id);
    }

    @GetMapping("/dashboard-stats")
    public Map<String, Long> getDashboardStats() {

        Map<String, Long> stats = new HashMap<>();

        stats.put(
                "totalCases",
                caseAnalysisRepository.count());

        stats.put(
                "highPriorityCases",
                caseAnalysisRepository.countByPriority("High"));

        stats.put(
                "negativeCases",
                caseAnalysisRepository.countBySentiment("Negative"));

        return stats;
    }

    @GetMapping("/analytics")
    public Map<String, Long> getAnalytics() {

        Map<String, Long> analytics = new HashMap<>();

        analytics.put(
                "highPriority",
                caseAnalysisRepository.countByPriority("High"));

        analytics.put(
                "mediumPriority",
                caseAnalysisRepository.countByPriority("Medium"));

        analytics.put(
                "lowPriority",
                caseAnalysisRepository.countByPriority("Low"));

        analytics.put(
                "negativeSentiment",
                caseAnalysisRepository.countBySentiment("Negative"));

        analytics.put(
                "neutralSentiment",
                caseAnalysisRepository.countBySentiment("Neutral"));

        analytics.put(
                "positiveSentiment",
                caseAnalysisRepository.countBySentiment("Positive"));
        analytics.put(
        "openCases",
        caseAnalysisRepository.countByStatus("Open"));

analytics.put(
        "inProgressCases",
        caseAnalysisRepository.countByStatus("In Progress"));

analytics.put(
        "resolvedCases",
        caseAnalysisRepository.countByStatus("Resolved"));

analytics.put(
        "closedCases",
        caseAnalysisRepository.countByStatus("Closed"));

analytics.put(
        "sathwikaCases",
        caseAnalysisRepository.countByAssignedTo("Sathwika"));

analytics.put(
        "arjunCases",
        caseAnalysisRepository.countByAssignedTo("Arjun"));

analytics.put(
        "supportTeamCases",
        caseAnalysisRepository.countByAssignedTo("Support Team"));

analytics.put(
        "unassignedCases",
        caseAnalysisRepository.countByAssignedTo("Unassigned"));

        return analytics;
    }
    @GetMapping("/cases/search")
public List<CaseAnalysis> searchCases(
        @RequestParam String keyword) {

    return caseAnalysisRepository
            .findByCustomerDescriptionContainingIgnoreCase(keyword);
}

    @GetMapping("/export-csv")
    public ResponseEntity<String> exportCsv() {

        List<CaseAnalysis> cases =
                caseAnalysisRepository.findAll();

        StringBuilder csv =
                new StringBuilder();

        csv.append(
                "ID,Category,Priority,Status,Assigned To,Sentiment,Created At\n");

        for (CaseAnalysis c : cases) {

            csv.append(c.getId()).append(",");
            csv.append(c.getCategory()).append(",");
            csv.append(c.getPriority()).append(",");
            csv.append(c.getStatus()).append(",");
            csv.append(c.getAssignedTo()).append(",");
            csv.append(c.getSentiment()).append(",");
            csv.append(c.getCreatedAt()).append("\n");
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=cases.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv.toString());
    }
}