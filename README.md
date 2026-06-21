*AI-Powered CRM Case Management System with Salesforce Integration

1.Overview

The AI-Powered CRM Case Management System is an intelligent customer support platform built using Spring Boot, PostgreSQL, Gemini AI, Salesforce CRM, and Gmail SMTP.

The application automates customer case analysis, categorization, prioritization, sentiment detection, Salesforce case creation, customer communication, and case lifecycle management.

This project demonstrates how Artificial Intelligence can be integrated with CRM systems to improve customer support efficiency and automate repetitive workflows.

---

2.Features
->AI-Powered Case Analysis
- Analyze customer support cases using Gemini AI
- Automatically determine:
  - Category
  - Priority
  - Sentiment
  - Summary
  - Suggested Resolution

-> Salesforce Integration
- Automatic Salesforce Case creation
- Salesforce Case ID tracking
- Salesforce synchronization status tracking
- REST API integration using OAuth 2.0

-> Customer Email Automation
- Capture customer email during case creation
- Automatically send resolution emails when a case is marked as Resolved
- Prevent duplicate notifications

-> Case Management
- Create customer support cases
- View case history
- Assign cases to support agents
- Update case status
- Track complete case lifecycle

-> Notes Management
- Add internal notes to cases
- View case notes history
- Improve support team collaboration

-> Search Functionality
Search support cases using:
- Category
- Customer Description

-> Analytics Dashboard
Track:
- Priority Distribution
- Sentiment Distribution
- Status Distribution
- Agent Assignments

-> Dashboard Statistics
Display:
- Total Cases
- High Priority Cases
- Negative Sentiment Cases

-> CSV Export
- Export all cases into CSV format
- Reporting and auditing support



3. Tech Stack

-> Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Web

-> Database
- PostgreSQL

-> AI Integration
- Google Gemini AI API

-> CRM Integration
- Salesforce REST API
- OAuth 2.0 Authentication

-> Email Service
- Gmail SMTP
- Spring Mail

-> Frontend
- HTML
- CSS
- JavaScript

-> Build Tool
- Maven

---
4. Project Architecture

Customer
    |
    v
Frontend (HTML/CSS/JavaScript)
    |
    v
Spring Boot Backend
    |
    +---------------------+
    |                     |
    v                     v
Gemini AI            PostgreSQL
    |                     |
    v                     |
AI Analysis              Cases
    |
    v
Salesforce CRM
    |
    v
Case Synchronization
    |
    v
Email Notification Service
