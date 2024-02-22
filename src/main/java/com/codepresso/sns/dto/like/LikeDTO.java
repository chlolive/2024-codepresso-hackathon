package com.codepresso.sns.dto.like;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record LikeDTO(long userId, long postId, Timestamp createdAt){
    public LikeDTO(long userId, long postId){
        this(userId, postId, Timestamp.valueOf(LocalDateTime.now()));
    }

}
