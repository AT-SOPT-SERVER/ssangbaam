package org.sopt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    public Post() {

    }

    public Post(String title) {
        blankTitle(title);
        maxLengthTitle(title);

        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    private void blankTitle(String title) {
        if(title.isBlank())
            throw new IllegalArgumentException("제목이 비어있습니다.");
    }

    private void maxLengthTitle(String title) {
        if(title.length() > 30)
            throw new IllegalArgumentException("제목이 30자 이상입니다.");
    }
}
