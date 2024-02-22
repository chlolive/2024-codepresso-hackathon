package com.codepresso.sns.controller;

import com.codepresso.sns.dto.like.LikeDTO;
import com.codepresso.sns.dto.post.PostViewAll;
import com.codepresso.sns.dto.post.UserId;
import com.codepresso.sns.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/post/{postId}/like")
    public @ResponseBody ResponseEntity<?> insertLike(@Valid @RequestBody UserId user, @Valid @PathVariable long postId){
        long userId = user.userId();
        long isValid = likeService.isValid(userId, postId);
        if(isValid < 0){
            return ResponseEntity.status(404).build();
        }
        Optional<LikeDTO> likeDTO = likeService.isAlreadyExist(userId, postId);
        if(likeDTO.isPresent()){
            return ResponseEntity.status(409).build();
        }
        long isAdded = likeService.insertLike(userId, postId);
        if(isAdded > 0){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Like successfully added to the post."));
        }
        return ResponseEntity.status(401).build();
    }
    @DeleteMapping("/post/{postId}/like")
    public @ResponseBody ResponseEntity<?> deleteLike(@Valid @RequestBody UserId user, @Valid @PathVariable long postId){
        long userId = user.userId();
        Optional<LikeDTO> likeDTO = likeService.isAlreadyExist(userId, postId);
        if(likeDTO.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        long isDeleted = likeService.deleteLike(userId, postId);
        if(isDeleted > 0){
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Like successfully removed from the post."));
        }
        return ResponseEntity.status(404).build();
    }
    @GetMapping(value = "/posts", params = "userId")
    public ResponseEntity<Map<String, Object>> getPostsByUserId(@RequestParam(required = true) long userId){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        List<Map<String, Object>> postList = likeService.getAllPostsByUser(userId);
        responseBody.put("posts", postList);
        return ResponseEntity.ok().body(responseBody);
    }
    @GetMapping(value = "/posts/sortedByLikes", params = "userId")
    public ResponseEntity<Map<String, Object>> getPostsSortedByLikes(@RequestParam(required = true) long userId){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        List<Map<String, Object>> postList = likeService.getAllPostsByUserWithLike(userId);
        responseBody.put("posts", postList);
        return ResponseEntity.ok().body(responseBody);
    }

}