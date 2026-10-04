package com.naveen.portfolio.controller;

import com.naveen.portfolio.entity.Skill;
import com.naveen.portfolio.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public List<Skill> getAll() {
        return skillService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById(@PathVariable Long id) {
        return skillService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Skill> create(@RequestBody Skill skill) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.create(skill));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> update(@PathVariable Long id, @RequestBody Skill skill) {
        return skillService.update(id, skill)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return skillService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
        @PostMapping("/bulk")
    public ResponseEntity<List<Skill>> createBulk(@RequestBody List<Skill> skills) {
        List<Skill> saved = skills.stream().map(skillService::create).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}