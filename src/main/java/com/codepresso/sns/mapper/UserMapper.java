package com.codepresso.sns.mapper;

import com.codepresso.sns.dto.UserDTO;
import com.codepresso.sns.vo.Summary;
import com.codepresso.sns.vo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (userName, email, password, introduction, occupation, birthday, city) VALUES (#{userName}, #{email}, #{password}, #{introduction}, #{occupation}, #{birthday}, #{city})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insert(User user);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(String email);

    @Select("SELECT * FROM user WHERE userId = #{userId}")
    User findById(long userId);

    @Select("SELECT * FROM user LEFT JOIN post ON post.userId = user.userId WHERE user.userId = #{userId}")
    Summary findByIdSummary(long userId);

    @Update("UPDATE user SET userName=#{userName}, introduction=#{introduction}, occupation = #{occupation}, city=#{city}, updatedAt = #{updatedAt}  WHERE userId = #{userId}")
    void patch(String userName, String introduction, String occupation, String city, long userId, Timestamp updatedAt);

    @Update("UPDATE user SET password=#{newPassword}, updatedAt = #{updatedAt} WHERE userId = #{userId}")
    long patchPassword(long userId, String newPassword, Timestamp updatedAt);


}
