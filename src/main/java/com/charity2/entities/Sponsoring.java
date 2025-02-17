package com.charity2.entities;

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
private Date date_signature;
private float montant_approuvee;
private String contrat ;

    @ManyToMany(mappedBy="sponsorings", cascade = CascadeType.ALL)
    private Set<Project> projects;
}
