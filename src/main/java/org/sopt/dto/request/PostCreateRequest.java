package org.sopt.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
public record PostCreateRequest(
         @JsonProperty("title")
         String title
 ) {

}
