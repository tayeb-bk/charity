package com.charity2.entities.Abdallah;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Sponsoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSponsoring;
private int project_id;
@ManyToOne
private Project project;
private Date date_signature;
private float montant_approuvee;
private String contrat ;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Project> projects;
}
