package org.sopt.domain;

public class Post {
    private int id;
    private String title;

    public Post(int id, String title) {
        blankTitle(title);
        maxLengthTitle(title);
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return this.id;
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
