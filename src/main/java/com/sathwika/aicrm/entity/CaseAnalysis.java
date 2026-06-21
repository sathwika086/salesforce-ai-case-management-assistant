package com.sathwika.aicrm.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "case_analysis")
public class CaseAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String priority;

    private String sentiment;

    private String status = "Open";

    private String assignedTo = "Unassigned";

    private String salesforceCaseId;

    private String salesforceSyncStatus;
    private String customerEmail;

private boolean emailSent = false;

    @Column(length = 2000)
    private String summary;

    @Column(length = 3000)
    private String suggestedResolution;

    @Column(length = 5000)
    private String customerDescription;

    private LocalDateTime createdAt;

    public CaseAnalysis() {
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getSalesforceCaseId() {
        return salesforceCaseId;
    }

    public void setSalesforceCaseId(String salesforceCaseId) {
        this.salesforceCaseId = salesforceCaseId;
    }

    public String getSalesforceSyncStatus() {
        return salesforceSyncStatus;
    }

    public void setSalesforceSyncStatus(String salesforceSyncStatus) {
        this.salesforceSyncStatus = salesforceSyncStatus;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSuggestedResolution() {
        return suggestedResolution;
    }

    public void setSuggestedResolution(String suggestedResolution) {
        this.suggestedResolution = suggestedResolution;
    }

    public String getCustomerDescription() {
        return customerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        this.customerDescription = customerDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getCustomerEmail() {
    return customerEmail;
}

public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
}

public boolean isEmailSent() {
    return emailSent;
}

public void setEmailSent(boolean emailSent) {
    this.emailSent = emailSent;
}
}