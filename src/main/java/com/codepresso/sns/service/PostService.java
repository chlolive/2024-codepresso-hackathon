package com.codepresso.sns.service;

import com.codepresso.sns.dto.post.PostByUser;
import com.codepresso.sns.dto.post.PostDTO;

import com.codepresso.sns.dto.user.UserDTO;
import com.codepresso.sns.dto.post.PostViewAll;
import com.codepresso.sns.dto.post.PostWithLike;
import com.codepresso.sns.mapper.PostMapper;
import com.codepresso.sns.vo.Post;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;
    private final UserService userService;

    public long createPost(PostDTO postDTO) {
        Post post = new Post(postDTO.userId(), postDTO.content());
        Optional<UserDTO> userDTO = userService.getUserById(postDTO.userId());
        if(userDTO.isEmpty()){
            return -1;
        }
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
        return postMapper.editPost(postId, userId, content);
    }
    public long updateLike(long postId, long userId, long offset){
        PostDTO postDTO= postMapper.findById(postId).toDTO();
        return postMapper.updateLike(postId, userId, postDTO.likeCount()+offset);
    }
    public long deletePost(long postId, long userId){
        return postMapper.deletePost(postId, userId);
    }

    public List<PostViewAll> getAllPosts() {
        return postMapper.findAll();
    }

    public List<PostWithLike> getAllPostsOrderByLike(){
        return postMapper.findAllwithLike();
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