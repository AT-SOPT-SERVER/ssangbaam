package org.sopt.dto.response;

import org.sopt.domain.Post;

public record PostResponse(
        Long postId,
        String title
) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle());
    }
}
