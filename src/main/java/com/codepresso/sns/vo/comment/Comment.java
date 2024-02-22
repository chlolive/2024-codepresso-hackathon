package com.codepresso.sns.vo.comment;


import com.codepresso.sns.dto.comment.CommentDTO;
import com.codepresso.sns.dto.comment.GetCommentDTO;
import com.codepresso.sns.dto.comment.PostCommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Comment {
    long commentId;
    long postId;
    long userId;
    String comment;
    Timestamp createdAt;
    Timestamp updatedAt;

    public Comment(long postId, long userId, String comment, Timestamp createdAt, Timestamp updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public CommentDTO toCommentDTO() {
        return new CommentDTO(commentId, postId, userId, comment, createdAt, updatedAt);
    }
    public PostCommentDTO toPostCommentDTO() {
        return new PostCommentDTO(commentId, postId, userId, comment, createdAt);
    }


}
