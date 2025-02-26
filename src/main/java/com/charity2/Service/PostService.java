package com.charity2.Service;

import com.charity2.entities.Post;

import java.util.List;

public interface PostService {
    Post savePost(Post post);
    List<Post> getPosts();
}
