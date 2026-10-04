package com.naveen.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String subtitle;

    @Column(length = 2000)
    private String description;

    private String techStack;

    private String liveUrl;

    private String githubUrl;

    private String architectureImage;

    private Integer displayOrder;
}