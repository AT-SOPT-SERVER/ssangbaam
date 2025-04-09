package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.io.IOException;
import java.util.List;

public class PostController {
    private PostService postService = new PostService();

    public void createPost(String title) {
        postService.createdPost(title);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        return postService.updatePostTitle(updateId, newTitle);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }
}
