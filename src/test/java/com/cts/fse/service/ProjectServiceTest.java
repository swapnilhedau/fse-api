package com.cts.fse.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.cts.fse.domain.Project;
import com.cts.fse.repository.ProjectRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

	@InjectMocks
	private ProjectService projectService;

	@Mock
	private ProjectRepository projectRepository;

	private static final Integer PROJECT_ID = Integer.valueOf(1);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(projectService, "projectRepository", projectRepository);
	}

	@Test
	public void testAddProject() {

		Project addProject = new Project();
		addProject.setProjectId(PROJECT_ID);
		addProject.setProjectName("projectName");
		addProject.setStartDate(new Date());
		addProject.setEndDate(new Date());
		addProject.setPriority(1);
		addProject.setProjectStatus("ACTIVE");
		when(projectRepository.save(addProject)).thenReturn(addProject);

		Project addedpProject = projectService.addProject(addProject);

		assertNotNull(addedpProject);
		assertTrue(addedpProject.getProjectName().equalsIgnoreCase(addProject.getProjectName()));

	}

	@Test
	public void testEditProject() {

		Project editProject = new Project();
		editProject.setProjectName("projectName");
		when(projectRepository.save(editProject)).thenReturn(editProject);

		projectService.editProject(editProject);

	}

	@Test
	public void testGetAllProjects() {

		List<Project> listOfProjects = new ArrayList<>();
		Project project1 = new Project();

		Project project2 = new Project();

		listOfProjects.add(project1);
		listOfProjects.add(project2);

		when(projectRepository.findAll()).thenReturn(listOfProjects);

		List<Project> allProjects = projectService.getAllProjects();

		assertNotNull(allProjects);
		assertEquals(2, allProjects.size());

	}

	@Test
	public void testGetProjectById() {

		Project project = new Project();
		project.setProjectName("projectName");

		Optional<Project> optionalProject = Optional.of(project);

		when(projectRepository.findById(PROJECT_ID)).thenReturn(optionalProject);

		Optional<Project> projectById = projectService.getProjectById(PROJECT_ID);
		assertNotNull(projectById);
		if (projectById.isPresent()) {
			assertEquals(projectById.get().getProjectName(), project.getProjectName());
		}

	}

}
