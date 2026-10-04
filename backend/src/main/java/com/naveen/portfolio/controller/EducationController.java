package com.naveen.portfolio.controller;

import com.naveen.portfolio.entity.Education;
import com.naveen.portfolio.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @GetMapping
    public List<Education> getAll() {
        return educationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getById(@PathVariable Long id) {
        return educationService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Education> create(@RequestBody Education education) {
        return ResponseEntity.status(HttpStatus.CREATED).body(educationService.create(education));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Education>> createBulk(@RequestBody List<Education> list) {
        List<Education> saved = list.stream().map(educationService::create).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Education> update(@PathVariable Long id, @RequestBody Education education) {
        return educationService.update(id, education)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return educationService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}