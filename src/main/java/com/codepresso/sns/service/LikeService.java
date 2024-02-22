package com.codepresso.sns.service;

import com.codepresso.sns.dto.like.LikeDTO;
import com.codepresso.sns.dto.post.PostViewAll;
import com.codepresso.sns.dto.post.PostWithLike;
import com.codepresso.sns.mapper.LikeMapper;
import com.codepresso.sns.vo.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeMapper likeMapper;
    private final UserService userService;
    private final PostService postService;
    public long insertLike(long userId, long postId){
        long isAdded = likeMapper.insertLike(userId, postId);
        if(isAdded > 0){
            return postService.updateLike(postId, userId, 1);
        }else{
            return -1;
        }
    }
    public long deleteLike(long userId, long postId){
        long isDeleted = likeMapper.deleteLike(userId, postId);
        if(isDeleted > 0){
            return postService.updateLike(postId, userId, -1);
        }else{
            return -1;
        }
    }

    public Optional<LikeDTO> isAlreadyExist(long userId, long postId){
        return Optional.ofNullable(likeMapper.get(userId, postId)).map(Like::toDTO);
    }


    public long isValid(long userId, long postId){
        if(userService.getUserById(userId).isEmpty()){
            return -1;
        }
        if(postService.getPostById(postId).isEmpty()){
            return -1;
        }
        return 1;
    }

    public List<Map<String, Object>> getAllPostsByUser(long userId){
        List<PostViewAll> postList = postService.getAllPosts();
        List<Map<String, Object>> postMap = new ArrayList<>();
        for(PostViewAll post : postList){
            Map<String, Object> info = new LinkedHashMap<>();
            info.put("postId", post.getPostId());
            info.put("userId", post.getUserId());
            info.put("userName", post.getUserName());
            info.put("content", post.getContent());
            info.put("createdAt", post.getCreatedAt());

            Optional<Like> like = Optional.ofNullable(likeMapper.get(userId, post.getPostId()));
            if (like.isEmpty()) {
                info.put("likedByUser", false);
            } else {
                info.put("likedByUser", true);
            }

            postMap.add(info);
        }
        return postMap;
    }

    public List<Map<String, Object>> getAllPostsByUserWithLike(long userId){
        List<PostWithLike> postList = postService.getAllPostsOrderByLike();
        List<Map<String, Object>> postMap = new ArrayList<>();
        for(PostWithLike post : postList){
            Map<String, Object> info = new LinkedHashMap<>();
            info.put("postId", post.getPostId());
            info.put("userId", post.getUserId());
            info.put("userName", post.getUserName());
            info.put("content", post.getContent());
            info.put("createdAt", post.getCreatedAt());
            info.put("likecount", post.getLikeCount());

            Optional<Like> like = Optional.ofNullable(likeMapper.get(userId, post.getPostId()));
            if (like.isEmpty()) {
                info.put("likedByUser", false);
            } else {
                info.put("likedByUser", true);
            }

            postMap.add(info);
        }
        return postMap;
    }



}