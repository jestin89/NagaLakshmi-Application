package com.nagas.backend.repository;

import com.nagas.backend.entity.ApplicationAttachment;
import com.nagas.backend.model.AttachedResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationAttachmentRepository extends JpaRepository<ApplicationAttachment, Integer> {


    ApplicationAttachment findByUserId(int userId);
}
