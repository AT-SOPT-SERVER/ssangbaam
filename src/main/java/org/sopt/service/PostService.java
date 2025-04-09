package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.utils.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class PostService {
    private LocalDateTime recentCreatedTime = null;
    private PostRepository postRepository = new PostRepository();

    public void createdPost(String title) {
        if(postRepository.ExistedTitle(title))
            throw new IllegalArgumentException("중복된 제목을 가진 게시물이 존재합니다.");

        LocalDateTime threeMinutesAgo = LocalDateTime.now().minusMinutes(3);
        if(recentCreatedTime != null && threeMinutesAgo.isBefore(recentCreatedTime))
            throw new IllegalStateException("최근 게시물을 작성한지 3분이 지나지 않았습니다.");

        int postId = IdGenerator.generateId();
        Post post = new Post(postId, title);

        postRepository.save(post);
        recentCreatedTime = LocalDateTime.now();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        return postRepository.update(updateId, newTitle);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.search(keyword);
    }
}
