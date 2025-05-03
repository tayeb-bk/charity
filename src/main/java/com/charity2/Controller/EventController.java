package com.charity2.Controller;

import com.charity2.Service.EventService;
import com.charity2.Service.WeatherDataService;
import com.charity2.entities.Event;
import com.charity2.entities.EventCategory; // Import EventCategory
import com.charity2.entities.WeatherData;
import com.charity2.Service.EventPdfExporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map; // Import the Map class
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final WeatherDataService weatherDataService;
    private final EventPdfExporter pdfExporter;

    @Autowired
    public EventController(EventService eventService, WeatherDataService weatherDataService, EventPdfExporter pdfExporter) {
        this.eventService = eventService;
        this.weatherDataService = weatherDataService;
        this.pdfExporter = pdfExporter;
    }

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

    @GetMapping("/weather")
    public ResponseEntity<WeatherData> getWeather(@RequestParam LocalDate date, @RequestParam String location) {
        WeatherData weatherData = weatherDataService.getWeatherData(date, location);
        if (weatherData != null) {
            return ResponseEntity.ok(weatherData);
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
            if (eventDetails.getCategories() != null) {
                existingEvent.setCategories(eventDetails.getCategories());
            }
            Event updatedEvent = eventService.updateEvent(existingEvent);
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventService.getEventById(id).isPresent()) {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- New Endpoint for Category Percentages ---
    @GetMapping("/category-percentages")
    public ResponseEntity<Map<EventCategory, Double>> getEventCategoryPercentages() {
        Map<EventCategory, Double> percentages = (Map<EventCategory, Double>) eventService.getAllEvents(); // Call the service method
        return ResponseEntity.ok(percentages);
    }

    // --- Refactored PDF Report Endpoint ---
    @GetMapping("/report/pdf")
    public ResponseEntity<Resource> generateEventsPdfReport() {
        try {
            // 1. Fetch data
            List<Event> events = eventService.getAllEvents(); // Keep using getAllEvents for PDF

            // 2. Generate PDF using the dedicated exporter service
            ByteArrayOutputStream baos = pdfExporter.generateEventListPdf(events);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            InputStreamResource resource = new InputStreamResource(bais);

            // 3. Prepare Headers
            String filename = "events_report.pdf";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());
            headers.setContentLength(baos.size());

            // 4. Return ResponseEntity
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);

        } catch (Exception e) {
            System.err.println("Error generating PDF report: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}

