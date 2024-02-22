package com.codepresso.sns.dto.user;

import java.util.Date;

public record SummaryDTO(long userId, String userName, long likeCount, long followingCount, long followerCount, String introduction, String occupation) {

    public SummaryDTO(String userName, long likeCount, long followingCount, long followerCount, String introduction, String occupation) {
        this(0, userName, likeCount, followingCount, followerCount, introduction, occupation);
    }


}
