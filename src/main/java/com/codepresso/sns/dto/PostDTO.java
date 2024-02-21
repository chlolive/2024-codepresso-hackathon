package com.codepresso.sns.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record PostDTO (long postId, long userId, String content, LocalDateTime createdAt, LocalDateTime updatedAt){
    public PostDTO(long postId, long userId, String content) {
        this(postId, userId, content, LocalDateTime.now(), LocalDateTime.now());
    }
}