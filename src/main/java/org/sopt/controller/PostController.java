package org.sopt.controller;

import org.sopt.dto.request.post.PostCreateRequest;
import org.sopt.dto.request.post.PostPatchRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(
            @RequestHeader Long userId,
            @RequestBody PostCreateRequest request
    ) {
        return ResponseEntity.created(postService.createdPost(request, userId)).build();
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePostById(
            @RequestHeader Long userId,
            @PathVariable Long postId
    ) {
        postService.deletePostById(postId, userId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/post/{postId}")
    public ResponseEntity<?> updatePost(
            @RequestHeader Long userId,
            @PathVariable Long postId,
            @RequestBody PostPatchRequest request
    ) {
        return ResponseEntity.ok(postService.updatePost(userId,postId, request));
    }

    @GetMapping(value = "/posts", params = "keyword")
    public ResponseEntity<?> searchPostsByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(postService.searchPostsByKeyword(keyword));
    }
}
