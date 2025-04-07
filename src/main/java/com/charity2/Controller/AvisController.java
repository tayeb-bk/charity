package com.charity2.Controller;

import com.charity2.Service.AvisService;
import com.charity2.dto.AvisRequest;
import com.charity2.entities.Avis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @PostMapping("/add-avis")
    public Avis createAvis(@RequestBody AvisRequest avisRequest) {
        return avisService.createAvis(avisRequest);
    }

    @GetMapping("/get-avis")
    public List<Avis> getAllAvis() {
        return avisService.getAllAvis();
    }

    @GetMapping("/get-avis/{id}")
    public ResponseEntity<Avis> getAvisById(@PathVariable Long id) {
        Optional<Avis> avis = avisService.getAvisById(id);

        if (avis.isPresent()) {
            return ResponseEntity.ok(avis.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/put-avis/{id}")
    public ResponseEntity<Avis> updateAvis(@PathVariable Long id, @RequestBody AvisRequest avisDetails) {
        Optional<Avis> optionalAvis = avisService.getAvisById(id);

        if (optionalAvis.isPresent()) {
            Avis existingAvis = optionalAvis.get();

            if (avisDetails.getContent() != null && !avisDetails.getContent().isEmpty()) { // Corrected to getContent()
                existingAvis.setContenue(avisDetails.getContent()); // Corrected to setContent()
            }

            if (avisDetails.getCategories() != null) {
                existingAvis.setCategories(avisDetails.getCategories());
            }

            if (avisDetails.getTags() != null) {
                existingAvis.setTags(avisDetails.getTags());
            }

            // Assuming you have a way to update the event in AvisService
            // if (avisDetails.getEvent() != null) {
            //     existingAvis.setEvent(avisDetails.getEvent());
            // }

            Avis updatedAvis = avisService.updateAvis(existingAvis);
            return ResponseEntity.ok(updatedAvis);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete-avis/{id}")
    public ResponseEntity<Void> deleteAvis(@PathVariable Long id) {
        if (avisService.getAvisById(id).isPresent()) {
            avisService.deleteAvis(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categories/{category}")
    public List<Avis> getAvisByCategory(@PathVariable String category) {
        return avisService.getAvisByCategory(category);
    }

    @GetMapping("/tags/{tag}")
    public List<Avis> getAvisByTag(@PathVariable String tag) {
        return avisService.getAvisByTag(tag);
    }
}