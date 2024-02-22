package com.codepresso.sns.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Getter
public class PostWithLike {
    private long postId;
    private long userId;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private long likeCount;

    public PostWithLike(long postId, long userId, String userName, String content, LocalDateTime createdAt, long likeCount) {
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
    }
}