package org.sopt.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostPatchRequest(
        @JsonProperty("title")
        String newTitle
) {
}
