package com.codepresso.sns.controller;


import com.codepresso.sns.dto.PostDTO;
import com.codepresso.sns.service.PostService;
import com.codepresso.sns.vo.Post;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    //1. Post 작성
    @PostMapping("/post")
    public ResponseEntity<PostDTO> create(@Valid @RequestBody PostDTO postDTO){
        long postId = postService.create(postDTO);
        return postService.getPostById(postId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        //return ResponseEntity.created(URI.create("/posts/" + postId)).build();
    }
    //2. 전체 Post 조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }


}