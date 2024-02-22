package com.codepresso.sns.service;

import com.codepresso.sns.dto.post.PostByUser;
import com.codepresso.sns.dto.post.PostDTO;

import com.codepresso.sns.dto.user.UserDTO;
import com.codepresso.sns.dto.post.PostViewAll;
import com.codepresso.sns.mapper.PostMapper;
import com.codepresso.sns.vo.Post;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;
    private final UserService userService;

    public long createPost(PostDTO postDTO) {
        Post post = new Post(postDTO.userId(), postDTO.content());
        postMapper.create(post);
        return post.getPostId();
    }

    public Optional<PostDTO> getPostById(long postId) {
        return Optional.ofNullable(postMapper.findById(postId)).map(Post::toDTO);
    }
    public PostViewAll getEditedPostById(long postId){
        return postMapper.findEditedPost(postId);
    }
    public long editPost(long postId, long userId, String content){
        Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
        return postMapper.editPost(postId, userId, content, updatedAt);

    }
    public long deletePost(long postId, long userId){
        return postMapper.deletePost(postId, userId);
    }

    public Map<String, List<PostViewAll>> getAllPosts() {
        List<PostViewAll> postList = postMapper.findAll();
        Map<String, List<PostViewAll>> responseBody = new HashMap<>();
        responseBody.put("posts", postList);
        return responseBody;
    }
    public Map<String, Object> getPostsByUserId(long userId){
        List<PostByUser> postList = postMapper.findByUserId(userId);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("userId", userId);
        String userName = getUserName(userId);
        responseBody.put("userName", userName);
        responseBody.put("posts", postList);
        return responseBody;
    }

    public String getUserName(long userId){
        Optional<UserDTO> optionalUserDTO = userService.getUserById(userId);
        if(optionalUserDTO.isPresent()){
            return optionalUserDTO.get().userName();
        }else{
            return null;
        }
    }


}