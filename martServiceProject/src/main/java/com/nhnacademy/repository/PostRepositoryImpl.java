package com.nhnacademy.repository;

import com.nhnacademy.domain.Post;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepositoryImpl implements PostRepository {

    private final Map<Long, Post> postMap = new HashMap<>();

    private long id = 0;

    @Override
    public Post register(String userName, String title, String type, String content,
            List<String> fileName) {
        Post post = Post.builder()
                .id(++id)
                .userName(userName)
                .title(title)
                .type(type)
                .content(content)
                .dateTime(LocalDateTime.now())
                .fileName(fileName)
                .replyCheck(false)
                .build();

        postMap.put(id, post);
        return post;
    }

    public Post getPost(long id) {
        return postMap.get(id);
    }

    @Override
    public List<Post> getList() {
        return new ArrayList<>(postMap.values());
    }
}
