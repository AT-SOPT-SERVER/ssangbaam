package org.sopt.dto.response.post;

import org.sopt.domain.Post;

public record PostDetailResponse(
        String title,
        String content,
        String userName
) {
    public static PostDetailResponse from(Post post) {
        return new PostDetailResponse(post.getTitle(), post.getContent(), post.getUser().getName());
    }
}
