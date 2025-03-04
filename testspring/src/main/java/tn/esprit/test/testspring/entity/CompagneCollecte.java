package tn.esprit.test.testspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompagneCollecte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titre;
    String description;
    float objectif;
    float montantCollecte;
    Date dateDebut;
    Date dateFin;
    @Enumerated(EnumType.STRING)
    MoyenPaiement moyenPaiement;

    @OneToMany(mappedBy = "compagneCollecte", cascade = CascadeType.ALL)
            @JsonIgnore
    List<Commenter> commenters;

}