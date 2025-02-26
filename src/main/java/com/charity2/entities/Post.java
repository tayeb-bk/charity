package com.charity2.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 5000)
    private String content;

    private String postedBy;
    private Date Date;

    private String img;

    private int likeCount;
    private int viewCount;

    @ElementCollection
    private List<String> tags;


}
