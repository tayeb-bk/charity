package com.charity2.Repository;

import com.charity2.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    // You can add custom query methods here if needed, but JpaRepository provides basic CRUD operations.
}
