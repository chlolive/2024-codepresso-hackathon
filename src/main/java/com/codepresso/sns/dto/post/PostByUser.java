package com.codepresso.sns.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Getter
public class PostByUser {
    private long postId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostByUser(long postId, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}