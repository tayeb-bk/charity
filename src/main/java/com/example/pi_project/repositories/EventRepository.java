package com.example.pi_project.repositories;

import com.example.pi_project.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EventRepository extends JpaRepository<Event,Long>{
}
