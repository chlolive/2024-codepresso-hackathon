package com.codepresso.sns.mapper;

import com.codepresso.sns.vo.comment.Comment;
import com.codepresso.sns.vo.comment.CommentList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {


    @Select("SELECT COUNT(*) FROM post WHERE postId = #{postId}")
    int checkPost(long postId);

    @Insert("INSERT INTO comment (postId, userId, comment) VALUES (#{postId}, #{userId}, #{comment})")
    @Options(useGeneratedKeys = true, keyProperty = "commentId")
    int insertComment(Comment comment);


    @Select("SELECT * FROM comment WHERE commentId = #{commentId}")
    Comment findCommentById(long commentId);


    @Select("SELECT c.commentId, c.postId, c.userId, u.userName, c.comment, c.createdAt, c.updatedAt FROM comment c LEFT JOIN user u ON u.userId = c.userId WHERE c.postId=#{postId} ORDER BY c.createdAt")
    List<CommentList> findAllComments(long postId);

}
