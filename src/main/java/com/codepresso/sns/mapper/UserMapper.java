package com.codepresso.sns.mapper;

import com.codepresso.sns.vo.user.Summary;
import com.codepresso.sns.vo.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (userName, email, password, introduction, occupation, birthday, city) VALUES (#{userName}, #{email}, #{password}, #{introduction}, #{occupation}, #{birthday}, #{city})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Select("SELECT user.email FROM user WHERE email=#{email}")
    Optional<String> checkEmail(String email);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(String email);

    @Select("SELECT * FROM user WHERE userId = #{userId}")
    User findById(long userId);

    @Select("SELECT u.userId, u.userName, p.likeCount, u.followingCount, u.followerCount, u.introduction, u.occupation FROM user u LEFT JOIN post p ON p.userId = u.userId WHERE u.userId = #{userId}")
    Summary findByIdSummary(long userId);

    @Update("UPDATE user SET userName=#{userName}, introduction=#{introduction}, occupation = #{occupation}, city=#{city}, updatedAt = #{updatedAt}  WHERE userId = #{userId}")
    void patch(String userName, String introduction, String occupation, String city, long userId, Timestamp updatedAt);

    @Update("UPDATE user SET password=#{newPassword}, updatedAt = #{updatedAt} WHERE userId = #{userId}")
    long patchPassword(long userId, String newPassword, Timestamp updatedAt);


}
