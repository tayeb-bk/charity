package tn.esprit.test.testspring.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Commenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String contenu;
    LocalDate dateCommentaire;

    @ManyToOne
    CompagneCollecte compagneCollecte;
}
