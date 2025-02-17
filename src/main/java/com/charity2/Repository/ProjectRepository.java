package com.charity2.Repository;

import com.charity2.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface ProjectRepository extends JpaRepository<Project, Long> {
}
