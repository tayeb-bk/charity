package com.charity2.entities.Aziz.serviceImpl;

import com.charity2.entities.Aziz.entity.Training;
import com.charity2.entities.Aziz.repo.TrainingRepository;
import com.charity2.entities.Aziz.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public Training getTrainingById(Long id) {
        return trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with id: " + id));
    }

    @Override
    public Training updateTraining(Long id, Training trainingDetails) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        training.setTitle(trainingDetails.getTitle());
        training.setDescription(trainingDetails.getDescription());
        training.setCategory(trainingDetails.getCategory());
        training.setTrainer(trainingDetails.getTrainer());
        training.setLocation(trainingDetails.getLocation());
        training.setStartDate(trainingDetails.getStartDate());
        training.setEndDate(trainingDetails.getEndDate());
        training.setCapacity(trainingDetails.getCapacity());

        return trainingRepository.save(training);
    }
    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }
}
