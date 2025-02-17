package com.example.pi_project.controlles;

import com.example.pi_project.entities.Event;
import com.example.pi_project.services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/Event")
public class AvisControlle {
    IEventService iEventService;

    @GetMapping("/getEvents")

    public List<Event> retrieveAllEvents() {
        return iEventService.getAllEvent();
    }

    @PostMapping("/addEvent")
    public Event addEvent(@RequestBody Event f) {
        return iEventService.addEvent(f);
    }

    @PutMapping("/updateEvent")
    public Event updateEvent(@RequestBody Event f) {
        return iEventService.updateEvent(f);
    }

    @GetMapping("/getEventById/{idEvent}")
    public Event retrieveEvent(@PathVariable long idEvent) {
        return iEventService.getEventById(idEvent);
    }

    @DeleteMapping("/getDeleteEventById/{idEvent}")
    public void removeEvent(@PathVariable long idEvent) {
        iEventService.deleteEvent(idEvent);
    }
}
