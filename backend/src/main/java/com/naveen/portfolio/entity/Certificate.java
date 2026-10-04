package com.naveen.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "certificates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String issuer;

    private String issueDate;

    private String credentialId;

    private String verifyUrl;

    private String imageUrl;

    private Integer displayOrder;
}