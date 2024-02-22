package com.codepresso.sns.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.ALWAYS)
public record PostDTO (long postId, long userId, String content, long likeCount, long commentCount, LocalDateTime createdAt, LocalDateTime updatedAt){
    public PostDTO(long postId, long userId, String content, long likeCount, long commentCount) {
        this(postId, userId, content, likeCount, commentCount, LocalDateTime.now(), LocalDateTime.now());
    }


}