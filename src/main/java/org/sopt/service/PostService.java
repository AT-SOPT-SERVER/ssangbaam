package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.Post;
import org.sopt.dto.request.PostCreateRequest;
import org.sopt.dto.request.PostPatchRequest;
import org.sopt.dto.response.PostListResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public URI createdPost(PostCreateRequest request) {
        LocalDateTime recentCreatedTime = null;

        if(postRepository.existsByTitle(request.title()))
            throw new IllegalArgumentException("중복된 제목을 가진 게시물이 존재합니다.");

        if(!postTimeCheck(recentCreatedTime))
            throw new IllegalStateException("최근 게시물을 작성한지 3분이 지나지 않았습니다.");

        Post post = new Post(request.title());

        postRepository.save(post);
        recentCreatedTime = LocalDateTime.now();

        return URI.create("/post/" + post.getId());
    }

    @Transactional
    public PostListResponse getAllPosts() {
        List<PostResponse> postList = postRepository.findAll()
                .stream()
                .map(PostResponse::from)
                .toList();

        return PostListResponse.of(postList);
    }

    @Transactional
    public PostResponse getPostById(Long postId) {
        Post post = getPost(postId);

        return PostResponse.from(post);
    }

    @Transactional
    public void deletePostById(Long id) {
        Post post = getPost(id);

        postRepository.delete(post);
    }

    @Transactional
    public PostResponse updatePostTitle(Long postId, PostPatchRequest request) {
        Post post = getPost(postId);

        post.updateTitle(request.newTitle());
        return PostResponse.from(post);
    }

    @Transactional
    public PostListResponse searchPostsByKeyword(String keyword) {
        List<PostResponse> postList = postRepository.findByTitleContaining(keyword).stream()
                .map(PostResponse::from)
                .toList();

        return PostListResponse.of(postList);
    }

    private Boolean postTimeCheck(LocalDateTime recentCreatedTime) {
        LocalDateTime threeMinutesAgo = LocalDateTime.now().minusMinutes(3);
        if(recentCreatedTime != null && threeMinutesAgo.isBefore(recentCreatedTime))
            return false;
        return true;
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    }
}
