package com.charity2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long projectId;

        private String titre;
        private int associationId;
        private Date dateSignature;
        private long budget;

        @Enumerated(EnumType.STRING)
        private Type_sponsoring type_sponsoring;

        @Enumerated(EnumType.STRING)
        private Project_Status project_status;

        @ManyToMany(cascade = CascadeType.ALL)
        private Set<Sponsoring> sponsorings;
}
