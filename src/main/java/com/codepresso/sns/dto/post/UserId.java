package com.codepresso.sns.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UserId(long userId) {
    public UserId {
    }
}