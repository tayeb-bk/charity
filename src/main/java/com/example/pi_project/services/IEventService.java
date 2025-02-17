package com.example.pi_project.services;

import com.example.pi_project.entities.Event;

import java.util.List;

public interface IEventService {
    Event addEvent(Event event);
    List<Event> getAllEvent();
    Event getEventById(long idEvent);
    void deleteEvent(long idEvent);
    Event updateEvent(Event event);
}
