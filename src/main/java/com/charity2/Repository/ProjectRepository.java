package com.charity2.Repository;

import com.charity2.entities.Abdallah.Project;
import com.charity2.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
