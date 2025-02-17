package com.charity2.Controller;
import com.charity2.entities.Delivery;
import com.charity2.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/add-delivery")
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @GetMapping("/get-deliveries")
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/get-delivery/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        Optional<Delivery> delivery = deliveryService.getDeliveryById(id);

        if (delivery.isPresent()) {
            return ResponseEntity.ok(delivery.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put-delivery/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable Long id, @RequestBody Delivery deliveryDetails) {
        Optional<Delivery> optionalDelivery = deliveryService.getDeliveryById(id);

        if (optionalDelivery.isPresent()) {
            Delivery existingDelivery = optionalDelivery.get();
            if (deliveryDetails.getDonationId() != null) {
                existingDelivery.setDonationId(deliveryDetails.getDonationId());
            }
            if (deliveryDetails.getPickupLocation() != null && !deliveryDetails.getPickupLocation().isEmpty()) {
                existingDelivery.setPickupLocation(deliveryDetails.getPickupLocation());
            }
            if (deliveryDetails.getDropOffLocation() != null && !deliveryDetails.getDropOffLocation().isEmpty()) {
                existingDelivery.setDropOffLocation(deliveryDetails.getDropOffLocation());
            }
            if (deliveryDetails.getStatus() != null) {
                existingDelivery.setStatus(deliveryDetails.getStatus());
            }
            Delivery updatedDelivery = deliveryService.updateDelivery(existingDelivery);
            return ResponseEntity.ok(updatedDelivery);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete-delivery/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        if (deliveryService.getDeliveryById(id).isPresent()) {
            deliveryService.deleteDelivery(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}