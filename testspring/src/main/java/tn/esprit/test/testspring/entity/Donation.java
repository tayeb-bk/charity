package tn.esprit.test.testspring.entity;



import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    String prenom;
    String adresse;
    String numero;
    String description;
    @Enumerated(EnumType.STRING)
    MoyenPaiement moyenPaiement;
    @Enumerated(EnumType.STRING)
    TypeDonation typeDonation;
    @Enumerated(EnumType.STRING)
    Etat etat;

    private Double amount;


}
