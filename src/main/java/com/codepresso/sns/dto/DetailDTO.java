package com.codepresso.sns.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record DetailDTO(long userId, String userName, String email, long postCount, long followingCount, long followerCount, String introduction, String occupation, LocalDate birthday, String city, Timestamp createdAt, Timestamp updatedAt) {


}
