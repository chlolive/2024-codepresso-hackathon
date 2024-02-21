package com.codepresso.sns.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record SignUpDTO(@NotBlank String userName, @Email String email , @NotBlank String password, String introduction, String occupation, Date birthday, String city) {

}
