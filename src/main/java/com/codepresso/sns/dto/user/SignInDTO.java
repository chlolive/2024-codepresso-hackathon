package com.codepresso.sns.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record SignInDTO(long userId, String userName, @Email String email, @NotBlank String password) {

    public SignInDTO(String userName, String email, String password) {
        this(0, userName, email, password);
    }
}

