package com.cts.fse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.fse.domain.Project;
import com.cts.fse.repository.ProjectRepository;

@Service
public class ProjectService {
	private final ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public Project addProject(Project project) {
		return projectRepository.save(project);
	}

	public void editProject(Project project) {
		projectRepository.save(project);
	}

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public Optional<Project> getProjectById(Integer projectId) {
		return projectRepository.findById(projectId);
	}

}
