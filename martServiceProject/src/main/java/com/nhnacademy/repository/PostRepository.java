package com.nhnacademy.repository;

import com.nhnacademy.domain.Post;
import java.util.List;

public interface PostRepository {

    Post register(String userName, String title, String type, String content,
            List<String> fileName);


    Post getPost(long id);

    List<Post> getList();
}
