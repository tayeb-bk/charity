package com.example.pi_project.services;

import com.example.pi_project.entities.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsImpl implements IEventService{
    @Override
    public Event addEvent(Event event) {
        return null;
    }

    @Override
    public List<Event> getAllEvent() {
        return List.of();
    }

    @Override
    public Event getEventById(long idEvent) {
        return null;
    }

    @Override
    public void deleteEvent(long idEvent) {

    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }
}
