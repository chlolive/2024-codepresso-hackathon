package com.codepresso.sns.vo;

import com.codepresso.sns.dto.like.LikeDTO;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Getter
public class Like {
    long postId;
    long userId;
    Timestamp createdAt;
    public Like(long userId, long postId, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public LikeDTO toDTO() {
        return new LikeDTO(userId, postId);
    }
}