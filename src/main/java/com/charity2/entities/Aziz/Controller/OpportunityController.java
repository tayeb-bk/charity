package com.charity2.entities.Aziz.Controller;

import com.charity2.entities.Aziz.entity.Opportunity;
import com.charity2.entities.Aziz.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;

    // 📌 1. Ajouter une opportunité
    @PostMapping
    public ResponseEntity<Opportunity> createOpportunity(@RequestBody Opportunity opportunity) {
        Opportunity newOpportunity = opportunityService.createOpportunity(opportunity);
        return ResponseEntity.ok(newOpportunity);
    }

    // 📌 2. Récupérer toutes les opportunités
    @GetMapping
    public ResponseEntity<List<Opportunity>> getAllOpportunities() {
        return ResponseEntity.ok(opportunityService.getAllOpportunities());
    }

    // 📌 3. Récupérer une opportunité par ID
    @GetMapping("/{id}")
    public ResponseEntity<Opportunity> getOpportunityById(@PathVariable Long id) {
        return ResponseEntity.ok(opportunityService.getOpportunityById(id));
    }


    // 📌 5. Supprimer une opportunité
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpportunity(@PathVariable Long id) {
        opportunityService.deleteOpportunity(id);
        return ResponseEntity.noContent().build();
    }
}
