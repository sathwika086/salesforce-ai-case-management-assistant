package com.sathwika.aicrm.controller;

import com.sathwika.aicrm.service.SalesforceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesforceTestController {

    private final SalesforceService salesforceService;

    public SalesforceTestController(SalesforceService salesforceService) {
        this.salesforceService = salesforceService;
    }

    @GetMapping("/salesforce/case/{id}")
    public String createCaseFromDb(@PathVariable Long id) {
        return salesforceService.createCaseFromDb(id);
    }
}