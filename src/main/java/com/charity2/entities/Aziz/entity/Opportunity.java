package com.charity2.entities.Aziz.entity;

import com.charity2.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

    @Entity
    public class Opportunity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String description;

        @Enumerated(EnumType.STRING)
        private Categorie category;

        private String location;

        @ManyToOne
        @JoinColumn(name = "posted_by")
        private User postedBy;

        @Temporal(TemporalType.DATE)
        private Date datePosted;

        @Temporal(TemporalType.DATE)
        private Date deadline;

        @Enumerated(EnumType.STRING)
        private Type type;

        // Getters et Setters


}
