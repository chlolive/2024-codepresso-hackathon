package com.codepresso.sns.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record UserDTO(long userId, String userName, String email, long postCount, long followingCount, long followerCount, String introduction, String occupation, Date birthday, String city, Timestamp createdAt, Timestamp updatedAt) {

    public UserDTO(String userName, String email) {
        this(0, userName, email, 0, 0, 0, null, null, null, null, new Timestamp(System.currentTimeMillis()) , new Timestamp(System.currentTimeMillis()));
    }

}
