package com.codepresso.sns.dto;


import jakarta.validation.constraints.NotBlank;


public record PasswordDTO(@NotBlank String currentPassword , @NotBlank String newPassword) {


}
