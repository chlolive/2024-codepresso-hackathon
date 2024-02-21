package com.codepresso.sns.vo;

import com.codepresso.sns.dto.UserDTO;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter

public class User {
    long userId;
    String userName;
    String email;
    String password;
    long postCount;
    long followingCount;
    long followerCount;
    String introduction;
    String occupation;
    Date birthday;
    String city;
    Timestamp createdAt;
    Timestamp updatedAt;

    public User(String userName, String email, String password, String introduction, String occupation, Date birthday, String city) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.postCount = 0;
        this.followingCount = 0;
        this.followerCount = 0;
        this.introduction = introduction;
        this.occupation = occupation;
        this.birthday = birthday;
        this.city = city;
        this.createdAt =  new Timestamp(System.currentTimeMillis());
        this.updatedAt =  new Timestamp(System.currentTimeMillis());
    }

    public UserDTO toDTO() {
        return new UserDTO(userId, userName, email, postCount, followingCount, followerCount, introduction, occupation, birthday, city, createdAt, updatedAt);
    }
}
