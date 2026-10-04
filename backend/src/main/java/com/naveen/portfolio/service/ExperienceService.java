package com.naveen.portfolio.service;

import com.naveen.portfolio.entity.Experience;
import com.naveen.portfolio.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public List<Experience> getAll() {
        return experienceRepository.findAllByOrderByDisplayOrderAsc();
    }

    public Optional<Experience> getById(Long id) {
        return experienceRepository.findById(id);
    }

    public Experience create(Experience experience) {
        experience.setId(null);
        return experienceRepository.save(experience);
    }

    public Optional<Experience> update(Long id, Experience updated) {
        return experienceRepository.findById(id).map(existing -> {
            existing.setCompany(updated.getCompany());
            existing.setRole(updated.getRole());
            existing.setStartDate(updated.getStartDate());
            existing.setEndDate(updated.getEndDate());
            existing.setDescription(updated.getDescription());
            existing.setDisplayOrder(updated.getDisplayOrder());
            return experienceRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!experienceRepository.existsById(id)) {
            return false;
        }
        experienceRepository.deleteById(id);
        return true;
    }
}