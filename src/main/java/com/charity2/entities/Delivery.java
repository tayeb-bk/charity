package com.charity2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@ToString
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long donationId;
    private String pickupLocation;
    private String dropOffLocation;

    @Enumerated(EnumType.STRING)
    private Delivery_Status status;
}
