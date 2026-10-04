package com.naveen.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "experience")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String role;

    private String startDate;

    private String endDate;

    @Column(length = 2000)
    private String description;

    private Integer displayOrder;
}