package com.projectmanager.projectmanager.services;

import com.projectmanager.projectmanager.domain.Project;
import com.projectmanager.projectmanager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        // Logic

        return projectRepository.save(project);
    }
}
