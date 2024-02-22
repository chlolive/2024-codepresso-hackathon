package com.codepresso.sns.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Getter
public class PostByUser {
    private long postId;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public PostByUser(long postId, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.postId = postId;
        this.content = content;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = this.createdAt;
    }
}