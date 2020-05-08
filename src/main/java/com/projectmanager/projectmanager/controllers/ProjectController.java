package com.projectmanager.projectmanager.controllers;

import com.projectmanager.projectmanager.domain.Project;
import com.projectmanager.projectmanager.services.MapValidationErrorsService;
import com.projectmanager.projectmanager.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorsService mapValidationErrorsService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project , BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorsService.MapValidationErrorsService(result);

        if(errorMap != null) {
            return errorMap;
        }

        Project newProject = projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
    }
}
