package com.naveen.portfolio.service;

import com.naveen.portfolio.entity.Project;
import com.naveen.portfolio.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAll() {
        return projectRepository.findAllByOrderByDisplayOrderAsc();
    }

    public Optional<Project> getById(Long id) {
        return projectRepository.findById(id);
    }

    public Project create(Project project) {
        project.setId(null);
        return projectRepository.save(project);
    }

    public Optional<Project> update(Long id, Project updated) {
        return projectRepository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setSubtitle(updated.getSubtitle());
            existing.setDescription(updated.getDescription());
            existing.setTechStack(updated.getTechStack());
            existing.setLiveUrl(updated.getLiveUrl());
            existing.setGithubUrl(updated.getGithubUrl());
            existing.setArchitectureImage(updated.getArchitectureImage());
            existing.setDisplayOrder(updated.getDisplayOrder());
            return projectRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!projectRepository.existsById(id)) {
            return false;
        }
        projectRepository.deleteById(id);
        return true;
    }
}