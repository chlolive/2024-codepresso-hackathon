package com.codepresso.sns.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.apache.tomcat.jni.Time;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.ALWAYS)
public record PostDTO (long postId, long userId, String content, long likeCount, long commentCount, Timestamp createdAt, Timestamp updatedAt){
    public PostDTO(long postId, long userId, String content, long likeCount, long commentCount) {
        this(postId, userId, content, likeCount, commentCount, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
    }


}