package com.codepresso.sns.vo.comment;

import com.codepresso.sns.dto.comment.GetCommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommentList {
    long commentId;
    long postId;
    long userId;
    String userName;
    String comment;
    Timestamp createdAt;
    Timestamp updatedAt;

    public GetCommentDTO toGetCommentDTO() {
        return new GetCommentDTO(commentId, postId, userId, userName, comment, createdAt, updatedAt);
    }
}
