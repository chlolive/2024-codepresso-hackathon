package com.codepresso.sns.vo;


import com.codepresso.sns.dto.post.PostDTO;
import com.codepresso.sns.dto.post.PostViewAll;
import lombok.Data;
import lombok.Getter;

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
    public Post(long userId, String content) {
        this.userId = userId;
        this.content = content;
        this.likeCount = 0;
        this.commentCount = 0;
        this.createdAt =  LocalDateTime.now();
        this.updatedAt =  LocalDateTime.now();
    }
    public PostDTO toDTO() {
        return new PostDTO(postId, userId, content, likeCount, commentCount);
    }
}