package com.charity2.Service;

import com.charity2.Repository.PostRepository;
import com.charity2.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post) {
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setDate(new Date());

        return postRepository.save(post);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }


}
