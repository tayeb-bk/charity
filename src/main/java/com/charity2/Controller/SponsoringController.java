package com.charity2.Controller;
import com.charity2.Service.SponsoringService;
import com.charity2.entities.Sponsoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/sponsoring")
public class SponsoringController {
    @Autowired
    private SponsoringService sponsoringService;

    @PostMapping("/add-sponsoring")
    public Sponsoring createSponsoring(@RequestBody Sponsoring sponsoring) {
        return sponsoringService.createSponsoring(sponsoring);
    }

    @GetMapping("/get-sponsorings")
    public List<Sponsoring> getAllSponsoring() {
        return sponsoringService.getAllSponsorings();
    }

    @GetMapping("/get-sponsoring/{id}")
    public ResponseEntity<Sponsoring> getSponsoringById(@PathVariable Long id) {
        Optional<Sponsoring> sponsoring = sponsoringService.getSponsoringById(id);

        if (sponsoring.isPresent()) {
            return ResponseEntity.ok(sponsoring.get());
        } else return ResponseEntity.notFound().build();
    }
    @PutMapping("/put-sponsoring/{id}")
    public ResponseEntity<Sponsoring> updateSponsoring(@PathVariable long id, @RequestBody Sponsoring sponsoringDetails) {
        Optional<Sponsoring> optionalSponsoring = sponsoringService.getSponsoringById(id);

        if (optionalSponsoring.isPresent()) {
            Sponsoring existingSponsoring = optionalSponsoring.get();
            if (sponsoringDetails.getProject_id() != 0) {
                existingSponsoring.setProject_id(sponsoringDetails.getProject_id());
            }
            if (sponsoringDetails.getDate_signature() != null) {
                existingSponsoring.setDate_signature(sponsoringDetails.getDate_signature());
            }
            if (sponsoringDetails.getMontant_approuvee() != 0) {
                existingSponsoring.setMontant_approuvee(sponsoringDetails.getMontant_approuvee());
            }
            if (sponsoringDetails.getContrat() != null && !sponsoringDetails.getContrat().isEmpty()) {
                existingSponsoring.setContrat(sponsoringDetails.getContrat());
            }
            if (sponsoringDetails.getProjects() != null) {
                existingSponsoring.setProjects(sponsoringDetails.getProjects());
            }
            Sponsoring updatedSponsoring = sponsoringService.updateSponsoring(existingSponsoring);
            return ResponseEntity.ok(updatedSponsoring);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("delete-sponsoring/{id}")
    public ResponseEntity<Void> deleteSponsoring(@PathVariable Long id) {
        if (sponsoringService.getSponsoringById(id).isPresent()) {
            sponsoringService.deleteSponsoring(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}