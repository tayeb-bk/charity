package com.charity2.Controller;
import com.charity2.Service.EventService;
import com.charity2.entities.Delivery;
import com.charity2.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping("/add-event")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/get-events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/get-event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);

        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put-event/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Optional<Event> optionalEvent = eventService.getEventById(id);

        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();

            if (eventDetails.getTitle() != null && !eventDetails.getTitle().isEmpty()) {
                existingEvent.setTitle(eventDetails.getTitle());
            }
            if (eventDetails.getDescription() != null && !eventDetails.getDescription().isEmpty()) {
                existingEvent.setDescription(eventDetails.getDescription());
            }
            if (eventDetails.getDate() != null) {
                existingEvent.setDate(eventDetails.getDate());
            }
            if (eventDetails.getLocation() != null && !eventDetails.getLocation().isEmpty()) {
                existingEvent.setLocation(eventDetails.getLocation());
            }
            if (eventDetails.getStatus() != null && !eventDetails.getStatus().isEmpty()) {
                existingEvent.setStatus(eventDetails.getStatus());
            }
            if (eventDetails.getAssociationId() != 0) {
                existingEvent.setAssociationId(eventDetails.getAssociationId());
            }
            if (eventDetails.getCapacity() > 0) {
                existingEvent.setCapacity(eventDetails.getCapacity());
            }
            if (eventDetails.getAviss() != null && !eventDetails.getAviss().isEmpty()) {
                existingEvent.setAviss(eventDetails.getAviss());
            }

            Event updatedEvent = eventService.updateEvent(existingEvent);
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("delete-event/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        if (eventService.getEventById(id).isPresent()) {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

