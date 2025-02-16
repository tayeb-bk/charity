package com.charity2.Repository;

import com.charity2.entities.Abdallah.Delivery;
import com.charity2.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
