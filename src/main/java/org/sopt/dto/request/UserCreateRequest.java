package org.sopt.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCreateRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty("email")
        String email
) {
}
