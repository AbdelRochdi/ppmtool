package com.rochdi.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rochdi.ppmtool.domain.Project;
import com.rochdi.ppmtool.exceptions.ProjectIdException;
import com.rochdi.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public ProjectService() {
	}

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project id: "+ project.getProjectIdentifier().toUpperCase() + " already exists ");
		}
	}
	
	public Project findProjectById(String projectIdentifier) {
		
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		
		if (project == null) {
			throw new ProjectIdException("this project id doesn't exist");
		}
		
		return project;
		
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if (project == null) {
			throw new ProjectIdException("Cannot delete, project with ID: " + projectId + " doesn't exist.");
		}
		
		projectRepository.delete(project);
	}
}
