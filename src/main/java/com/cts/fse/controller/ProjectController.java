package com.cts.fse.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.domain.Project;
import com.cts.fse.domain.User;
import com.cts.fse.service.ProjectService;
import com.cts.fse.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ProjectService projectService;
	private final UserService userService;

	public ProjectController(ProjectService projectService, UserService userService) {
		this.projectService = projectService;
		this.userService = userService;
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addProject(@RequestBody Project project) {
		project.setProjectStatus("ACTIVE");
		logger.info("-- add project --");
		Project savedProject = projectService.addProject(project);
		Optional<User> optionalUser = userService.getUserById(project.getUserId());
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setProjectId(project.getProjectId());
			userService.editUser(user);
			logger.info("-- user updated with project id --");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);

	}

	@PostMapping("/edit/{projectId}")
	public ResponseEntity<Project> editProject(@RequestBody Project project, @PathVariable int projectId) {
		logger.info("-- edit Project --");

		Optional<Project> projectOptional = projectService.getProjectById(projectId);

		if (!projectOptional.isPresent())
			return ResponseEntity.notFound().build();

		project.setProjectId(Integer.valueOf(projectId));
		projectService.editProject(project);

		return ResponseEntity.status(HttpStatus.CREATED).body(project);

	}

	@GetMapping("/allProjects")
	public List<Project> getAllProjects() {
		logger.info("-- get all Projects  --");

		return projectService.getAllProjects();

	}

	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable int projectId) {
		logger.info("-- get Project by id -- " + projectId);

		Optional<Project> project = projectService.getProjectById(projectId);

		if (!project.isPresent())
			return ResponseEntity.badRequest().build();

		return ResponseEntity.ok(project.get());

	}

	@GetMapping("/suspend/{projectId}")
	public ResponseEntity<Object> suspendProject(@PathVariable int projectId) {
		logger.info("-- suspend project with id -- " + projectId);

		Optional<Project> optionalProject = projectService.getProjectById(projectId);

		if (!optionalProject.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		Project toBeSuspendProject = optionalProject.get();
		toBeSuspendProject.setProjectStatus("SUSPENDED");

		projectService.editProject(toBeSuspendProject);

		return ResponseEntity.accepted().build();
	}

}
