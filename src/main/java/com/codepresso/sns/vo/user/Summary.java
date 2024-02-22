package com.codepresso.sns.vo.user;

import com.codepresso.sns.dto.user.SummaryDTO;

public class Summary {

    long userId;
    String userName;
    long likeCount;
    long followingCount;
    long followerCount;
    String introduction;
    String occupation;
    public SummaryDTO toSummaryDTO() {
        return new SummaryDTO(userId,userName, likeCount, followingCount, followerCount, introduction, occupation);
    }

}
