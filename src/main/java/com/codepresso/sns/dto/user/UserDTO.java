package com.codepresso.sns.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record UserDTO(long userId, String userName, String email, String introduction, String occupation, LocalDate birthday, String city) {

    public UserDTO(String userName, String email, String introduction, String occupation, LocalDate birthday, String city) {
        this(0, userName, email, introduction, occupation, birthday, city);
    }


}
