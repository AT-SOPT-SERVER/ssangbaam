package org.sopt.dto.request.post;


import com.fasterxml.jackson.annotation.JsonProperty;
public record PostCreateRequest(
         @JsonProperty("title")
         String title,

         @JsonProperty("content")
         String content
 ) {

}
