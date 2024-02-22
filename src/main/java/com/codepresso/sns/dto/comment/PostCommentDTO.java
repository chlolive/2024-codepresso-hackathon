package com.codepresso.sns.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;
import java.time.LocalDate;


@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record PostCommentDTO(long commentId, long postId, long userId, String comment, Timestamp createdAt) {

}
