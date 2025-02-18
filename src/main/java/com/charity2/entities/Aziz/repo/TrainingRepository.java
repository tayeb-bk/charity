package com.charity2.entities.Aziz.repo;

import com.charity2.entities.Aziz.entity.Categorie;
import com.charity2.entities.Aziz.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

   }

