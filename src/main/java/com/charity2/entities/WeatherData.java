package com.charity2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String location; // e.g., city name
    private String weatherCondition; // e.g., "Rain", "Clear", "Snow"
    private double temperature;
    // Add other relevant weather fields
    //getters and setters
}
