package com.charity2.entities.Aziz.service;

import com.charity2.entities.Aziz.entity.Training;

import java.util.List;

public interface TrainingService {
    Training createTraining(Training training);
    List<Training> getAllTrainings();
    Training getTrainingById(Long id);
    Training updateTraining(Long id, Training trainingDetails);
    void deleteTraining(Long id);
}
