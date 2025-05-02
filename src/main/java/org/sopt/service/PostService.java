package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.request.PostCreateRequest;
import org.sopt.dto.request.PostPatchRequest;
import org.sopt.dto.response.PostDetailResponse;
import org.sopt.dto.response.PostListResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Transactional
    public URI createdPost(PostCreateRequest request, Long userId) {
        User currentUser = userService.getUserById(userId);

        if(postRepository.existsByTitle(request.title()))
            throw new IllegalArgumentException("중복된 제목을 가진 게시물이 존재합니다.");

        Post post = new Post(request.title(), request.title(), currentUser);

        postRepository.save(post);

        return URI.create("/post/" + post.getId());
    }

    @Transactional
    public PostListResponse getAllPosts() {
        List<PostResponse> postList = postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostResponse::from)
                .toList();

        return PostListResponse.of(postList);
    }

    @Transactional
    public PostDetailResponse getPostById(Long postId) {
        Post post = getPost(postId);

        return PostDetailResponse.from(post);
    }

    @Transactional
    public void deletePostById(Long id, Long userId) {
        Post post = getPost(id);
        User currentUser = userService.getUserById(userId);

        if(currentUser == post.getUser()) {
            throw new IllegalArgumentException("삭제할 권한이 없는 사용자입니다.");
        }
        postRepository.delete(post);
    }
    @Transactional
    public PostDetailResponse updatePost(Long userId, Long postId, PostPatchRequest request) {
        User currentUser = userService.getUserById(userId);
        Post post = getPost(postId);

        if(currentUser == post.getUser()) {
            throw new IllegalArgumentException("수정할 권한이 없는 사용자입니다.");
        }

        post.updateTitleAndContent(request.title(), request.content());
        return PostDetailResponse.from(post);
    }

    @Transactional
    public PostListResponse searchPostsByKeyword(String keyword) {
        List<PostResponse> postList = postRepository.findByTitleContaining(keyword).stream()
                .map(PostResponse::from)
                .toList();

        return PostListResponse.of(postList);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    }
}
