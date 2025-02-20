package com.charity2.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

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
    Date dateDonation;
    @Enumerated(EnumType.STRING)
    MoyenPaiement moyenPaiement;
    @Enumerated(EnumType.STRING)
    TypeDonation typeDonation;
    @Enumerated(EnumType.STRING)
    Etat etat;

}