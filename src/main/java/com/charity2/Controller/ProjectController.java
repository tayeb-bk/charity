

package com.charity2.Controller;

import com.charity2.entities.Project;
import com.charity2.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/projects")
    public class ProjectController {

        @Autowired
        private ProjectService projectService;

        @PostMapping("/add-project")
        public Project createProject(@RequestBody Project project) {
            return projectService.createProject(project);
        }

        @GetMapping("/get-projects")
        public List<Project> getAllProjects() {
            return projectService.getAllProjects();
        }

        @GetMapping("/get-project/{id}")
        public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
            Optional<Project> project = projectService.getProjectById(id);

            if (project.isPresent()) {
                return ResponseEntity.ok(project.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }


       @PutMapping("put-project/{id}")
        public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
            Optional<Project> optionalProject = projectService.getProjectById(id);

            if (optionalProject.isPresent()) {
                Project existingProject = optionalProject.get();
                existingProject.setTitre(projectDetails.getTitre());
                existingProject.setAssociationId(projectDetails.getAssociationId());
                existingProject.setDateSignature(projectDetails.getDateSignature());
                existingProject.setBudget(projectDetails.getBudget());
                existingProject.setType_sponsoring(projectDetails.getType_sponsoring());
                existingProject.setProject_status(projectDetails.getProject_status());

                if (projectDetails.getSponsorings() != null) {
                    existingProject.setSponsorings(projectDetails.getSponsorings());
                }
                Project updatedProject = projectService.updateProject(existingProject);
                return ResponseEntity.ok(updatedProject);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        @DeleteMapping("delete-project/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectService.getProjectById(id).isPresent()) {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
