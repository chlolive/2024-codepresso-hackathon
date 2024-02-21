package com.codepresso.sns.mapper;

import com.codepresso.sns.vo.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    //1. Post 작성
    @Insert("INSERT INTO post (userId, content) VALUES ( #{userId}, #{content})")
    int insert(Post post);
    @Select("SELECT * FROM post WHERE postID = #{postId}")
    Post findById(long postId);

    @Select("SELECT * FROM post as POSTS")
    List<Post> findAll();





}