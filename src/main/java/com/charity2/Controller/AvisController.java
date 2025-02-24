package com.charity2.Controller;
import com.charity2.Service.AvisService;
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

    public Avis createAvis(@RequestBody Avis avis) {
        return avisService.createAvis(avis);
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
    public ResponseEntity<Avis> updateAvis(@PathVariable Long id, @RequestBody Avis avisDetails) {
        Optional<Avis> optionalAvis = avisService.getAvisById(id);

        if (optionalAvis.isPresent()) {
            Avis existingAvis = optionalAvis.get();

            if (avisDetails.getContenue() != null && !avisDetails.getContenue().isEmpty()) {
                existingAvis.setContenue(avisDetails.getContenue());
            }

            if (avisDetails.getEvent() != null) {
                existingAvis.setEvent(avisDetails.getEvent());
            }

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
}