package com.charity2.repository;

import com.charity2.entities.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    WeatherData findByDateAndLocation(LocalDate date, String location);
    boolean existsByDateAndLocation(LocalDate date, String location);
}
