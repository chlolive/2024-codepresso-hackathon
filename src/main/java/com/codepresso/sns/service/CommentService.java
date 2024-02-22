package com.codepresso.sns.service;


import com.codepresso.sns.dto.comment.CommentDTO;
import com.codepresso.sns.dto.comment.GetCommentDTO;
import com.codepresso.sns.dto.comment.PostCommentDTO;
import com.codepresso.sns.mapper.CommentMapper;
import com.codepresso.sns.vo.comment.Comment;
import com.codepresso.sns.vo.comment.CommentList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    public Optional<PostCommentDTO> getCommentById(long commentId) {
        return Optional.ofNullable(commentMapper.findCommentById(commentId)).map(Comment::toPostCommentDTO);
    }


    public Optional<PostCommentDTO> postNewComment(CommentDTO commentDTO, long postId) {
        int result = commentMapper.checkPost(postId);
        if(result==0 || commentDTO.comment()== null || commentDTO.userId()==0) {
            return Optional.empty();
        }
        Comment newComment = new Comment(postId, commentDTO.userId(), commentDTO.comment(), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        int check = commentMapper.insertComment(newComment);
        long commentId = newComment.getCommentId();
        return Optional.ofNullable(commentMapper.findCommentById(commentId)).map(Comment::toPostCommentDTO);

    }

    public Optional<List<GetCommentDTO>> getAllComments(long postId) {
        List<GetCommentDTO> result = commentMapper.findAllComments(postId).stream().map(CommentList::toGetCommentDTO).toList();
        if(result.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(result);
    }


}
