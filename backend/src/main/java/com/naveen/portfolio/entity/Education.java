package com.naveen.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "education")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String institution;

    @Column(nullable = false)
    private String degree;

    private String startYear;

    private String endYear;

    private String score;

    private Integer displayOrder;
}