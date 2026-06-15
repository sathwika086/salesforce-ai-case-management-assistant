package com.sathwika.aicrm.controller;

import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    public String analyzeCase(String description) {

        return """
                AI Analysis

                Category: Billing
                Priority: High
                Sentiment: Negative

                Customer Description:
                """ + description;
    }
}