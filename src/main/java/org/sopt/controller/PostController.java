package org.sopt.controller;

import org.sopt.dto.request.PostCreateRequest;
import org.sopt.dto.request.PostPatchRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostCreateRequest request) {
        return ResponseEntity.created(postService.createdPost(request)).build();
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
    public ResponseEntity<?> deletePostById(@PathVariable Long postId) {
        postService.deletePostById(postId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/post/{postId}")
    public ResponseEntity<?> updatePostTitle(@PathVariable Long postId, @RequestBody PostPatchRequest request) {
        return ResponseEntity.ok(postService.updatePostTitle(postId, request));
    }

    @GetMapping(value = "/posts", params = "keyword")
    public ResponseEntity<?> searchPostsByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(postService.searchPostsByKeyword(keyword));
    }
}
