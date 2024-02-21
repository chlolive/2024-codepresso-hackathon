package com.codepresso.sns.service;

import com.codepresso.sns.dto.PostDTO;
import com.codepresso.sns.mapper.PostMapper;
import com.codepresso.sns.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public long create(PostDTO postDTO){
        return postMapper.insert(new Post(postDTO.postId(), postDTO.userId(), postDTO.content()));
    }
    public Optional<PostDTO> getPostById(long postId){
        return Optional.ofNullable(postMapper.findById(postId)).map(Post::toDTO);
    }

    public List<PostDTO> getAllPosts() { return postMapper.findAll().stream().map(Post::toDTO).toList();}
}