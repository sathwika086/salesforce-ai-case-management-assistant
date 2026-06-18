package com.sathwika.aicrm.repository;

import com.sathwika.aicrm.entity.CaseNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseNoteRepository
        extends JpaRepository<CaseNote, Long> {

    List<CaseNote> findByCaseIdOrderByCreatedAtDesc(Long caseId);
}