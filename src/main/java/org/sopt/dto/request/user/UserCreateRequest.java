package org.sopt.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCreateRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty("email")
        String email
) {
}
