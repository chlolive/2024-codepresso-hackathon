package com.codepresso.sns.vo;


import com.codepresso.sns.dto.PostDTO;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Getter
public class Post {
    long postId;
    long userId;
    String content;
    long likeCount;
    long commentCount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    public Post(long postId, long userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt =  LocalDateTime.now();
        this.updatedAt =  LocalDateTime.now();
    }
    public PostDTO toDTO() {
        return new PostDTO(postId, userId, content);
    }
}
