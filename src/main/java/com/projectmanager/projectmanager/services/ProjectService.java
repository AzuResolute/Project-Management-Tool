package com.projectmanager.projectmanager.services;

import com.projectmanager.projectmanager.domain.Project;
import com.projectmanager.projectmanager.exceptions.ProjectIdException;
import com.projectmanager.projectmanager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project findByProjectIdentifier(String projectId) {
        String projectIdUpperCased = projectId.toUpperCase();

        Project project = findByProjectIdentifier(projectIdUpperCased);

        if(project == null) {
            throw new ProjectIdException("Project ID: " +
                    projectIdUpperCased +
                    " does not exist");
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveOrUpdateProject(Project project) {
        try {
            String upperCasedIdentifier = project.getProjectIdentifier().toUpperCase();
            project.setProjectIdentifier(upperCasedIdentifier);
            return projectRepository.save(project);

        } catch (Exception ex) {
            throw new ProjectIdException("Project ID: " +
                    project.getProjectIdentifier() +
                    " already exists");
        }
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if(project == null) {
            throw new ProjectIdException("Project ID " +
                    projectId +
                    " does not exist");
        }

        projectRepository.delete(project);
    }
}
