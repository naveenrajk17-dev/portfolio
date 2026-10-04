package com.naveen.portfolio.controller;

import com.naveen.portfolio.dto.ContactRequest;
import com.naveen.portfolio.entity.ContactMessage;
import com.naveen.portfolio.repository.ContactMessageRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactMessageRepository contactMessageRepository;

    @PostMapping
    public ResponseEntity<Map<String, String>> send(@Valid @RequestBody ContactRequest request) {
        ContactMessage entity = new ContactMessage();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setSubject(request.getSubject());
        entity.setMessage(request.getMessage());
        entity.setSentAt(LocalDateTime.now());
        contactMessageRepository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("status", "Message sent successfully"));
    }
}