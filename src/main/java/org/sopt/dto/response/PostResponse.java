package org.sopt.dto.response;

import org.sopt.domain.Post;

public record PostResponse(
        String title,
        String userName
) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getTitle(), post.getUser().getName());
    }
}
