package org.sopt.dto.response;

import org.sopt.domain.User;

public record UserResponse(
        Long userId,
        String name,
        String email
) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
