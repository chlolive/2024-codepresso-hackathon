package com.codepresso.sns.controller;

import com.codepresso.sns.dto.comment.CommentDTO;
import com.codepresso.sns.dto.comment.GetCommentDTO;
import com.codepresso.sns.dto.comment.PostCommentDTO;
import com.codepresso.sns.dto.user.SignUpDTO;
import com.codepresso.sns.dto.user.UserDTO;
import com.codepresso.sns.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //1. comment 작성
    @PostMapping("/{postId}/comment")
    public ResponseEntity<PostCommentDTO> postComment(@Valid @PathVariable long postId, @RequestBody CommentDTO commentDTO) {


        Optional<PostCommentDTO> postCommentDTO  = commentService.postNewComment(commentDTO, postId);
        if (postCommentDTO.isEmpty() ){
            if (commentDTO.comment() ==null || commentDTO.userId()==0){
                return ResponseEntity.status(400).build();
            }
            else {
                return ResponseEntity.status(404).build();
            }
        }

        return postCommentDTO.map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    //2.comment 리스트 조회
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<GetCommentDTO>> getComments(@Valid @PathVariable long postId) {
        Optional<List<GetCommentDTO>> list = commentService.getAllComments(postId);
        if(list.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return list.map(ResponseEntity::ok).orElse(ResponseEntity.ok().build());

    }



}
