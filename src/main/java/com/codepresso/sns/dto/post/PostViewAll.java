package com.codepresso.sns.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Getter
public class PostViewAll{
    private long postId;
    private long userId;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostViewAll(long postId, long userId, String userName, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}