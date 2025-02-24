package com.charity2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private Set<Avis> Aviss;
}
