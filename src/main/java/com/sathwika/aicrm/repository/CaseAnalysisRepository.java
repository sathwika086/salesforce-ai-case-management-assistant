package com.sathwika.aicrm.repository;

import com.sathwika.aicrm.entity.CaseAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseAnalysisRepository
        extends JpaRepository<CaseAnalysis, Long> {

    long countByPriority(String priority);

    long countBySentiment(String sentiment);

    long countByStatus(String status);

    long countByAssignedTo(String assignedTo);

    List<CaseAnalysis>
    findByCategoryContainingIgnoreCaseOrCustomerDescriptionContainingIgnoreCase(
            String category,
            String description);
}