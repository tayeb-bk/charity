package com.charity2.Service;

import com.charity2.entities.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(Project project);
    List<Project> getAllProjects();
    Optional<Project> getProjectById(Long id);
    Project updateProject(Project project);
    void deleteProject(Long id);
}