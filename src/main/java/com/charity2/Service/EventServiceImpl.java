package com.charity2.Service;

import com.charity2.Repository.AlertRepository;
import com.charity2.Repository.EventRepository;
import com.charity2.entities.Alert;
import com.charity2.entities.Event;
import com.charity2.entities.EventCategory;
import com.charity2.entities.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final WeatherDataService weatherDataService;
    private final AlertRepository alertRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${poster.generator.api.url}")
    private String posterGeneratorApiUrl;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository, WeatherDataService weatherDataService, AlertRepository alertRepository) {
        this.eventRepository = eventRepository;
        this.weatherDataService = weatherDataService;
        this.alertRepository = alertRepository;
    }

    @Override
    public Event createEvent(Event event) {
        if (event.getCategories() == EventCategory.SPORTS) {
            Date eventDateUtil = event.getDate();
            LocalDate eventDate = eventDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String eventLocation = event.getLocation();

            WeatherData weather = weatherDataService.getWeatherData(eventDate, eventLocation);
            if (weather != null && weather.getWeatherCondition().toLowerCase().contains("rain")) {
                Alert alert = new Alert();
                alert.setMessage("Rainy weather is predicted for this sports event.");
                alert.setEvent(event);
                alertRepository.save(alert);
            }
        }

        Event savedEvent = eventRepository.save(event);
        triggerPosterGeneration(savedEvent); // Consume the Python API after creating the event
        return savedEvent;
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
        Event updatedEvent = eventRepository.save(event);
        triggerPosterGeneration(updatedEvent); // Consume the Python API after updating the event
        return updatedEvent;
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private void triggerPosterGeneration(Event event) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Event> request = new HttpEntity<>(event, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    posterGeneratorApiUrl,
                    request,
                    Map.class
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Event poster generation triggered successfully. Response: " + response.getBody());
                // You can log or handle the response as needed
            } else {
                System.err.println("Failed to trigger event poster generation. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Error while calling poster generation API: " + e.getMessage());
        }
    }
}