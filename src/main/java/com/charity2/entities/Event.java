package com.charity2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date date;
    private String location;
    private String status;
    private int associationId;
    private int capacity;
    @Enumerated(EnumType.STRING) // Specify how the enum is stored in the database
    private EventCategory categories; // Changed to enum type
    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<Avis> Aviss;
}
