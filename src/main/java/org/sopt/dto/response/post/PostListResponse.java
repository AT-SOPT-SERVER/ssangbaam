package org.sopt.dto.response.post;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PostListResponse(
        @JsonProperty(value = "postList")
        List<PostResponse> postList
) {
        public static PostListResponse of(List<PostResponse> postList) {
                return new PostListResponse(postList);
        }
}
