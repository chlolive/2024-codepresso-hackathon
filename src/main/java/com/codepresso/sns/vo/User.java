package com.codepresso.sns.vo;

import com.codepresso.sns.dto.SignUpDTO;
import com.codepresso.sns.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public UserDTO toDTO() {
        return new UserDTO(userId, userName, email, introduction, occupation, birthday, city);
    }


}
