package com.codepresso.sns.mapper;

import com.codepresso.sns.dto.like.LikeDTO;
import com.codepresso.sns.vo.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LikeMapper {

    @Insert("INSERT INTO postLike (userId, postId) VALUES (#{userId}, #{postId})")
    long insertLike(long userId, long postId);
    @Select("SELECT * FROM postLike " +
            "WHERE userId = #{userId} AND postId = #{postId}")
    Like get(long userId, long postId);

    @Delete("DELETE FROM postLike " +
            "WHERE userId = #{userId} AND postId = #{postId}")
    long deleteLike(long userId, long postId);


}