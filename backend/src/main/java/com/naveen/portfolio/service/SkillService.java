package com.naveen.portfolio.service;

import com.naveen.portfolio.entity.Skill;
import com.naveen.portfolio.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public List<Skill> getAll() {
        return skillRepository.findAllByOrderByDisplayOrderAsc();
    }

    public Optional<Skill> getById(Long id) {
        return skillRepository.findById(id);
    }

    public Skill create(Skill skill) {
        skill.setId(null);
        return skillRepository.save(skill);
    }

    public Optional<Skill> update(Long id, Skill updated) {
        return skillRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setCategory(updated.getCategory());
            existing.setIconUrl(updated.getIconUrl());
            existing.setDisplayOrder(updated.getDisplayOrder());
            return skillRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!skillRepository.existsById(id)) {
            return false;
        }
        skillRepository.deleteById(id);
        return true;
    }
}