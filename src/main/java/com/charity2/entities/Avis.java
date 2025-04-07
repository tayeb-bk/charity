package com.charity2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenue;
    private String categories;
    private String tags;
    private float sentimentScore;
    private String sentenceSentiments;


    @ManyToOne
    Event event;
    //*
}
