package com.codepresso.sns.mapper;

import com.codepresso.sns.vo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (userName, email, password, introduction, occupation, birthday, city) VALUES (#{userName}, #{email}, #{password}, #{introduction}, #{occupation}, #{birthday}, #{city})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Select("SELECT *  FROM user WHERE email = #{email}")
    User findByEmail(String email);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(long id);


}
