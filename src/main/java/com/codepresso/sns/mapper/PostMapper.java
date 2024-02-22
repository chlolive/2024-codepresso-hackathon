package com.codepresso.sns.mapper;

import com.codepresso.sns.dto.post.PostByUser;
import com.codepresso.sns.dto.post.PostViewAll;
import com.codepresso.sns.dto.post.PostWithLike;
import com.codepresso.sns.vo.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    //1. Post 작성
    @Insert("INSERT INTO post (userId, content) VALUES ( #{userId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "postId")
    int create(Post post);
    @Select("SELECT * FROM post WHERE postID = #{postId}")
    Post findById(long postId);
    @Select("SELECT post.postId, post.userId, user.userName, post.content, post.createdAt, post.updatedAt" +
            " FROM post" +
            " JOIN user ON post.userID = user.userId" +
            " WHERE post.postId = #{postId}")
    PostViewAll findEditedPost(long postId);

    @Select("SELECT postId, content, createdAt, updatedAt FROM post " +
            "WHERE userId = #{userId} " +
            "ORDER BY postId desc")
    List<PostByUser> findByUserId(long userId);


    @Select("SELECT post.postId, post.userId, user.userName, post.content, post.createdAt, post.updatedAt" +
            " FROM post" +
            " Join user on post.userId = user.userId" +
            " ORDER BY post.postId DESC")
    List<PostViewAll> findAll();

    @Select("SELECT post.postId, post.userId, user.userName, post.content, post.createdAt, post.likeCount " +
            "FROM post " +
            "JOIN user ON post.userId = user.userId " +
            "ORDER BY post.likeCount DESC")
    List<PostWithLike> findAllwithLike();

    @Update("UPDATE post SET content = #{content} WHERE postId = #{postId} AND userId = #{userId}")
    int editPost(long postId, long userId, String content);
    @Update("UPDATE post SET likeCount = #{likeCount} WHERE postId = #{postId} AND userId = #{userId}")
    int updateLike(long postId, long userId, long likeCount);
    @Delete("DELETE FROM post WHERE postId = #{postId} AND userID = #{userId}")
    int deletePost(long postId, long userId);

}