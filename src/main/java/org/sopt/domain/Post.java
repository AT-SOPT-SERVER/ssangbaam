package org.sopt.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {

    }

    public Post(String title, String content, User user) {
        blankTitle(title);
        maxLengthTitle(title);
        validateContentLength(content);

        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private void blankTitle(String title) {
        if(title.isBlank())
            throw new IllegalArgumentException("제목이 비어있습니다.");
    }

    private void maxLengthTitle(String title) {
        if(title.length() > 30)
            throw new IllegalArgumentException("제목이 30자 이상입니다.");
    }

    private void validateContentLength(String content) {
        if (content.length() > 1000)
            throw new IllegalArgumentException("내용이 1000자 이상입니다.");
    }
}
