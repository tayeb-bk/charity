package com.charity2.Service;

import com.charity2.entities.Avis;
import com.charity2.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event createEvent(Event event);
    List<Event> getAllEvents();
    Optional<Event> getEventById(Long id);
    Event updateEvent(Event event);
    void deleteEvent(Long id);

}
