package com.codepresso.sns.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record SignUpDTO(@NotBlank String userName, @Email String email , @NotBlank String password, String introduction, String occupation, LocalDate birthday, String city) {



}
