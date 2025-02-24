package com.charity2.Service;

import com.charity2.Repository.AvisRepository;
import com.charity2.Repository.EventRepository;
import com.charity2.entities.Avis;
import com.charity2.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }
    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }
    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
