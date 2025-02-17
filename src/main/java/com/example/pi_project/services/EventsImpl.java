package com.example.pi_project.services;

import com.example.pi_project.entities.Event;
import com.example.pi_project.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsImpl implements IEventService{
    EventRepository eventRepository;

    @Override
    public List<Event> getAllEvent() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public Event getEventById(long idEvent) {
        return eventRepository.findById(idEvent).orElse(null);
    }

    @Override
    public Event addEvent(Event f) {
        return eventRepository.save(f);
    }

    @Override
    public Event updateEvent(Event f) {
        return eventRepository.save(f);
    }


    @Override
    public void deleteEvent(long idEvent) {
        eventRepository.deleteById(idEvent);

    }
}
