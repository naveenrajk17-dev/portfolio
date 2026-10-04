package com.naveen.portfolio.controller;

import com.naveen.portfolio.entity.Certificate;
import com.naveen.portfolio.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public List<Certificate> getAll() {
        return certificateService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getById(@PathVariable Long id) {
        return certificateService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Certificate> create(@RequestBody Certificate certificate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(certificateService.create(certificate));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Certificate>> createBulk(@RequestBody List<Certificate> certificates) {
        List<Certificate> saved = certificates.stream().map(certificateService::create).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> update(@PathVariable Long id, @RequestBody Certificate certificate) {
        return certificateService.update(id, certificate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return certificateService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}