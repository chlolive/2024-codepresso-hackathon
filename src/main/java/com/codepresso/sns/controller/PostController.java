package com.codepresso.sns.controller;


import com.codepresso.sns.dto.post.PostDTO;
import com.codepresso.sns.dto.post.PostViewAll;
import com.codepresso.sns.dto.post.UserId;
import com.codepresso.sns.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    //1. Post 작성
    @PostMapping("/post")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO){
        long postId = postService.createPost(postDTO);
        return postService.getPostById(postId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }
    //2. 전체 Post 조회
    @GetMapping("/posts")
    public ResponseEntity<Map<String, List<PostViewAll>>> getAllPosts() {
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    //4. 작성자 별 Post 조회
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<Map<String, Object>> getPostsByUserId(@PathVariable long userId){
        return ResponseEntity.ok().body(postService.getPostsByUserId(userId));

    }
    @PatchMapping("/post/{postId}")
    public ResponseEntity<PostViewAll> editPost(@PathVariable long postId, @RequestBody PostDTO postDTO){
        long isUpdated = postService.editPost(postId, postDTO.userId(), postDTO.content());
        if(isUpdated > 0){
            return ResponseEntity.ok().body(postService.getEditedPostById(postId));
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable long postId, @RequestBody UserId user){
        long userId = user.userId();
        long isDeleted = postService.deletePost(postId, userId);
        if(isDeleted > 0){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Post successfully deleted.");
            return ResponseEntity.ok().body(response);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }



}