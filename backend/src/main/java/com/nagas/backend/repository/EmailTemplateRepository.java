package com.nagas.backend.repository;

import com.nagas.backend.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer> {
    EmailTemplate findByTemplateName(String templateName);
}
