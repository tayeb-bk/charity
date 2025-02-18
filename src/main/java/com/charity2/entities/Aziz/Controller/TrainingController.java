package com.charity2.entities.Aziz.Controller;

import com.charity2.entities.Aziz.entity.Training;
import com.charity2.entities.Aziz.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    // 📌 1. Ajouter une formation
    @PostMapping("/add-training")
    public ResponseEntity<Training> createTraining(@RequestBody Training training) {
        Training newTraining = trainingService.createTraining(training);
        return ResponseEntity.ok(newTraining);
    }

    // 📌 2. Récupérer toutes les formations
    @GetMapping
    public ResponseEntity<List<Training>> getAllTrainings() {
        return ResponseEntity.ok(trainingService.getAllTrainings());
    }

    // 📌 3. Récupérer une formation par ID
    @GetMapping("/get-training/{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingService.getTrainingById(id));
    }
    @PutMapping("/put-training/{id}")
    public ResponseEntity<Training> updateTraining(@PathVariable Long id, @RequestBody Training trainingDetails) {
        Training updatedTraining = trainingService.updateTraining(id, trainingDetails);
        return ResponseEntity.ok(updatedTraining);
    }
    // 📌 5. Supprimer une formation
    @DeleteMapping("/delete-training/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }
}
