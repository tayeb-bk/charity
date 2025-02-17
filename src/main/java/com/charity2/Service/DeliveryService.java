package com.charity2.Service;

import com.charity2.entities.Delivery;
import java.util.List;
import java.util.Optional;


public interface DeliveryService {
    Delivery createDelivery(Delivery delivery);
    List<Delivery> getAllDeliveries();
    Optional<Delivery> getDeliveryById(Long id);
    Delivery updateDelivery(Delivery delivery);
    void deleteDelivery(Long id);
}
