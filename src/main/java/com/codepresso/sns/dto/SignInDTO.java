package com.codepresso.sns.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record SignInDTO(@Email String email, @NotBlank String password) {
}

