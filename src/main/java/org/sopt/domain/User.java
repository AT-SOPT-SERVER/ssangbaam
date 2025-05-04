package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.global.Constant;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Post> diaryEntities = new ArrayList<>();

    protected User() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User(String name, String email) {
        validateName(name);

        this.name = name;
        this.email = email;
    }

    private void validateName(String name) {
        if(name.length() > Constant.MAX_USER_NAME_LENGTH) {
            throw new IllegalArgumentException("유저 이름이 10글자 이상입니다.");
        }
    }
}
