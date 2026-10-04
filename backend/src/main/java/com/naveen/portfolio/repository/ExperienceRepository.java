package com.naveen.portfolio.repository;

import com.naveen.portfolio.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    List<Experience> findAllByOrderByDisplayOrderAsc();
}