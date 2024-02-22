package com.codepresso.sns.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record CommentDTO(long commentId, long postId, @NotBlank long userId, @NotBlank String comment, Timestamp createdAt, Timestamp updatedAt) {

//    public CommentDTO(long postId, long userId, String comment, Timestamp createdAt, Timestamp updatedAt) {
//        this(0, userId, comment, createdAt, updatedAt);
//    }
}
