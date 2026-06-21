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

```text
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
```
Case Synchronization
    |
    v
Email Notification Service
5. Work Flow

Step 1
Customer submits a support issue.

Step 2
Gemini AI analyzes the issue and generates:
Category
Priority
Sentiment
Summary
Suggested Resolution

Step 3
Case data is stored in PostgreSQL.

Step 4
A Salesforce Case is automatically created.

Step 5
Support team manages:
Assignments
Notes
Status updates

Step 6
When a case is marked as Resolved:
Customer receives an automated email notification.

6. Setup Instructions
Clone Repository
git clone https://github.com/sathwika086/salesforce-ai-case-management-assistant.git

-> Configure Application Properties
Create:
src/main/resources/application.properties
Example:
spring.datasource.url=jdbc:postgresql://localhost:5432/aicrm
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

gemini.api.key=YOUR_GEMINI_API_KEY

salesforce.client.id=YOUR_CLIENT_ID
salesforce.client.secret=YOUR_CLIENT_SECRET

spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_APP_PASSWORD
-> Run Application
mvn spring-boot:run
Application will be available at:
http://localhost:8080

7. Key Achievements
AI-powered customer case classification
Automated sentiment analysis
Salesforce CRM integration
Email automation workflow
Case assignment and lifecycle management
Analytics and reporting dashboard
Enterprise-style customer support workflow

Author
Developed by Sathwika Thangallapally

Technologies Used
Spring Boot | PostgreSQL | Gemini AI | Salesforce CRM | Gmail SMTP | HTML | CSS | JavaScript
⭐ If you found this project useful, consider starring the repository.

##Screenshots

<img width="1361" height="637" alt="Screenshot 2026-06-21 151947" src="https://github.com/user-attachments/assets/6cf45cb3-f182-4e2c-9935-7ef30d479ba5" />
<img width="1363" height="736" alt="Screenshot 2026-06-21 151711" src="https://github.com/user-attachments/assets/6a7badd4-9921-4732-8f72-3225c5ea30d0" />
<img width="1359" height="680" alt="image" src="https://github.com/user-attachments/assets/bb2ce0a1-156f-4c9b-9be4-d66d0128942c" />
<img width="1344" height="663" alt="image" src="https://github.com/user-attachments/assets/6d95d40e-37fe-4550-ab04-b5887109cd72" />




