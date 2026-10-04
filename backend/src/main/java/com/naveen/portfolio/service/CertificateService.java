package com.naveen.portfolio.service;

import com.naveen.portfolio.entity.Certificate;
import com.naveen.portfolio.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public List<Certificate> getAll() {
        return certificateRepository.findAllByOrderByDisplayOrderAsc();
    }

    public Optional<Certificate> getById(Long id) {
        return certificateRepository.findById(id);
    }

    public Certificate create(Certificate certificate) {
        certificate.setId(null);
        return certificateRepository.save(certificate);
    }

    public Optional<Certificate> update(Long id, Certificate updated) {
        return certificateRepository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setIssuer(updated.getIssuer());
            existing.setIssueDate(updated.getIssueDate());
            existing.setCredentialId(updated.getCredentialId());
            existing.setVerifyUrl(updated.getVerifyUrl());
            existing.setImageUrl(updated.getImageUrl());
            existing.setDisplayOrder(updated.getDisplayOrder());
            return certificateRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!certificateRepository.existsById(id)) {
            return false;
        }
        certificateRepository.deleteById(id);
        return true;
    }
}