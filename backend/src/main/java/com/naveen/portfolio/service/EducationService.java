package com.naveen.portfolio.service;

import com.naveen.portfolio.entity.Education;
import com.naveen.portfolio.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;

    public List<Education> getAll() {
        return educationRepository.findAllByOrderByDisplayOrderAsc();
    }

    public Optional<Education> getById(Long id) {
        return educationRepository.findById(id);
    }

    public Education create(Education education) {
        education.setId(null);
        return educationRepository.save(education);
    }

    public Optional<Education> update(Long id, Education updated) {
        return educationRepository.findById(id).map(existing -> {
            existing.setInstitution(updated.getInstitution());
            existing.setDegree(updated.getDegree());
            existing.setStartYear(updated.getStartYear());
            existing.setEndYear(updated.getEndYear());
            existing.setScore(updated.getScore());
            existing.setDisplayOrder(updated.getDisplayOrder());
            return educationRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!educationRepository.existsById(id)) {
            return false;
        }
        educationRepository.deleteById(id);
        return true;
    }
}