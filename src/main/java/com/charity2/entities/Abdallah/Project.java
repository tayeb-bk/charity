package com.charity2.entities.Abdallah;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@ToString
public class Project {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int projectId;
        private String titre;
        private int associationId;
        private Date dateSignature;
        private long budget;
        @Enumerated(EnumType.STRING)
        private Type_sponsoring type_sponsoring;
        @Enumerated(EnumType.STRING)
        private Project_Status project_status;
    @ManyToMany(mappedBy="projects", cascade = CascadeType.ALL)
    private Set<Sponsoring> sponsorings;

}

