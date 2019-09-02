package com.cts.fse.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cts.fse.domain.Project;
import com.cts.fse.domain.User;
import com.cts.fse.service.ProjectService;
import com.cts.fse.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectService projectService;

	@MockBean
	private UserService userService;

	@Test
	public void testAddProject() throws Exception {

		Project addProject = new Project();
		addProject.setProjectId(Integer.valueOf(1));
		addProject.setProjectName("projectName");
		addProject.setPriority(1);
		addProject.setUserId(Integer.valueOf(1));

		when(projectService.addProject(addProject)).thenReturn(addProject);

		User user = new User();
		user.setUserId(Integer.valueOf(1));
		Optional<User> optionalUser = Optional.of(user);
		when(userService.getUserById(Integer.valueOf(1))).thenReturn(optionalUser);

		String exampleUser = "{\"userId\":1,\"firstName\":\"James\",\"lastName\":\"Bond\",\"employeeId\":12345,\"projectId\":null,\"taskId\":null}";

		MvcResult result = mockMvc.perform(post("/project/add").accept(MediaType.APPLICATION_JSON).content(exampleUser)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();

		assertTrue(HttpStatus.CREATED.value() == result.getResponse().getStatus());

	}

	@Test
	public void testEditProject() throws Exception {

		Project editProject = new Project();
		editProject.setProjectId(Integer.valueOf(1));
		editProject.setProjectName("projectName");
		editProject.setPriority(1);
		editProject.setUserId(Integer.valueOf(1));

		Optional<Project> optionalProject = Optional.of(editProject);
		when(projectService.getProjectById(Integer.valueOf(1))).thenReturn(optionalProject);
		doNothing().when(projectService).editProject(editProject);

		String projectByIdString = "{\"projectId\":1,\"projectName\":\"projectName\",\"startDate\":null,\"endDate\":null,\"priority\":0,\"projectStatus\":null,\"userId\":null,\"noOfTasks\":null,\"completed\":null}";

		MvcResult result = mockMvc.perform(post("/project/edit/1").accept(MediaType.APPLICATION_JSON)
				.content(projectByIdString).contentType(MediaType.APPLICATION_JSON)).andReturn();

		assertTrue(HttpStatus.CREATED.value() == result.getResponse().getStatus());

	}

	@Test
	public void testEditProjectWithInvalidId() throws Exception {

		Optional<Project> optionalProject = Optional.empty();
		when(projectService.getProjectById(Integer.valueOf(1))).thenReturn(optionalProject);

		MvcResult result = mockMvc.perform(
				post("/user/edit/99").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertTrue(HttpStatus.NOT_FOUND.value() == result.getResponse().getStatus());

	}

	@Test
	public void testGetAllProjects() throws Exception {

		List<Project> projects = Lists.newArrayList();
		Project project = new Project();
		project.setProjectName("projectName");
		projects.add(project);

		when(projectService.getAllProjects()).thenReturn(projects);

		MvcResult result = mockMvc.perform(get("/project/allProjects")).andDo(print()).andExpect(status().isOk())
				.andReturn();

		assertTrue(HttpStatus.OK.value() == result.getResponse().getStatus());
		assertTrue(result.getResponse().getContentAsString().contains("projectName"));

	}

	@Test
	public void testGetProjectById() throws Exception {

		Project project = new Project();
		project.setProjectId(Integer.valueOf(1));
		project.setProjectName("projectName");

		Optional<Project> optionalProject = Optional.of(project);
		when(projectService.getProjectById(Integer.valueOf(1))).thenReturn(optionalProject);

		String projectByIdString = "{\"projectId\":1,\"projectName\":\"projectName\",\"startDate\":null,\"endDate\":null,\"priority\":0,\"projectStatus\":null,\"userId\":null,\"noOfTasks\":null,\"completed\":null}";

		MvcResult result = mockMvc.perform(get("/project/1")).andDo(print()).andReturn();

		assertTrue(HttpStatus.OK.value() == result.getResponse().getStatus());
		assertEquals(projectByIdString, result.getResponse().getContentAsString());
	}

	@Test
	public void testGetProjectByIdWithInvalidId() throws Exception {

		Optional<Project> optionalProject = Optional.empty();
		when(projectService.getProjectById(Integer.valueOf(1))).thenReturn(optionalProject);

		MvcResult result = mockMvc.perform(get("/project/99")).andDo(print()).andReturn();

		assertTrue(HttpStatus.BAD_REQUEST.value() == result.getResponse().getStatus());

	}

	@Test
	public void testSuspendProject() throws Exception {

		Project project = new Project();
		project.setProjectId(Integer.valueOf(1));
		project.setProjectName("projectName");

		Optional<Project> optionalProject = Optional.of(project);
		when(projectService.getProjectById(Integer.valueOf(1))).thenReturn(optionalProject);
		doNothing().when(projectService).editProject(project);

		MvcResult result = mockMvc.perform(get("/project/suspend/1")).andDo(print()).andReturn();

		assertTrue(HttpStatus.ACCEPTED.value() == result.getResponse().getStatus());

	}

	@Test
	public void testSuspendProjectWithInvalidId() throws Exception {

		Optional<Project> optionalProject = Optional.empty();
		when(projectService.getProjectById(Integer.valueOf(1))).thenReturn(optionalProject);

		MvcResult result = mockMvc.perform(get("/project/suspend/1")).andDo(print()).andReturn();

		assertTrue(HttpStatus.BAD_REQUEST.value() == result.getResponse().getStatus());

	}

}
