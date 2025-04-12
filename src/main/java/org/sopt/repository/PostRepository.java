package org.sopt.repository;

import org.sopt.domain.Post;
import org.sopt.utils.IdGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostRepository {
    List<Post> postList = new ArrayList<>();
    private static final String FILE_PATH = "src/main/java/org/sopt/assets/Post.txt";

    public PostRepository() {
        try {
            postList = loadFromFile();
            int maxId = postList.stream()
                    .map(post -> post.getId())
                    .max(Integer::compareTo)
                    .orElse(0);

            IdGenerator.initialize(maxId + 1);
        } catch (FileNotFoundException e) {
            System.err.println("파일을 찾을 수 없습니다.");
        }
    }

    public void save(Post post) {
        postList.add(post);
        try {
            saveToFile(post);
        } catch (IOException e) {
            System.out.println("파일을 저장하지 못했습니다.");
        }
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findById(int id) {
        for(Post post: postList) {
            if(post.getId() == id)
                return post;
        }

        return null;
    }

    public boolean delete(int id) {
        for(Post post: postList) {
            if(post.getId() == id) {
                postList.remove(postList.indexOf(post));
                return true;
            }
        }

        return false;
    }

    public boolean update(int id, String title) {
        for(Post post: postList) {
            if(post.getId() == id) {
                post.updateTitle(title);
                return true;
            }
        }
        return false;
    }

    public List<Post> search(String keyword) {
        return postList.stream()
                .filter(post -> post.getTitle().contains(keyword))
                .toList();
    }

    public boolean ExistedTitle(String title) {
        return postList.stream()
                       .anyMatch(post -> post.getTitle().equals(title));
    }

    private void saveToFile(Post post) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH, true);
        writer.write(post.getId() + ":" + post.getTitle() + "\n");
        writer.close();
    }
    private List<Post> loadFromFile() throws FileNotFoundException {
        List<Post> posts = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists())
            return posts;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            int id = Integer.parseInt(parts[0]);
            String title = parts[1];
            posts.add(new Post(id, title));
        }

        return posts;
    }
}
