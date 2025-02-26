package com.charity2.Controller;

import com.charity2.Service.PostService;
import com.charity2.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    @Autowired
    private PostService postService;


    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post)
    {
        try {
            Post createdPost = postService.savePost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
