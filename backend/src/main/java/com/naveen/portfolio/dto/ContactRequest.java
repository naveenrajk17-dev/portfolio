package com.naveen.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name is too long")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @Size(max = 150, message = "Subject is too long")
    private String subject;

    @NotBlank(message = "Message is required")
    @Size(max = 2000, message = "Message is too long")
    private String message;
}