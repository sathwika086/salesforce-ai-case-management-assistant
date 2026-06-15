package com.sathwika.aicrm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyzeCaseController {

    private final GeminiService geminiService;

    public AnalyzeCaseController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/analyze-case")
    public String analyzeCase(
            @RequestParam String description) {

        return geminiService.analyzeCase(description);
    }
}