package com.codepresso.sns.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record GetCommentDTO(long commentId, long postId, long userId, String userName, String comment, Timestamp createdAt, Timestamp updatedAt) {

    public GetCommentDTO(long postId, long userId, String userName, String comment, Timestamp createdAt, Timestamp updatedAt) {
        this(0, postId, userId, userName, comment, createdAt, updatedAt);
    }

}
