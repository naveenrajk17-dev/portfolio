package com.naveen.portfolio.controller;

import com.naveen.portfolio.entity.Experience;
import com.naveen.portfolio.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping
    public List<Experience> getAll() {
        return experienceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getById(@PathVariable Long id) {
        return experienceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Experience> create(@RequestBody Experience experience) {
        return ResponseEntity.status(HttpStatus.CREATED).body(experienceService.create(experience));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> update(@PathVariable Long id, @RequestBody Experience experience) {
        return experienceService.update(id, experience)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return experienceService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}