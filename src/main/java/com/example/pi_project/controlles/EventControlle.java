package com.example.pi_project.controlles;

import com.example.pi_project.entities.Event;
import com.example.pi_project.services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Event")
public class EventControlle {
    @Autowired
    private IEventService ieventService;

    @GetMapping("/getEvents")

    public List<Event> retrieveAllEvents() {
        return ieventService.getAllEvent();
    }

    @PostMapping("/addEvent")
    public Event addEvent(@RequestBody Event f) {
        return ieventService.addEvent(f);
    }

    @PutMapping("/updateEvent")
    public Event updateEvent(@RequestBody Event f) {
        return ieventService.updateEvent(f);
    }

    @GetMapping("/getEventById/{idEvent}")
    public Event retrieveEvent(@PathVariable long idEvent) {
        return ieventService.getEventById(idEvent);
    }

    @DeleteMapping("/getDeleteEventById/{idEvent}")
    public void removeEvent(@PathVariable long idEvent) {
        ieventService.deleteEvent(idEvent);
    }

}
