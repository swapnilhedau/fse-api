package com.cts.fse.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cts.fse.domain.ParentTask;
import com.cts.fse.domain.Task;
import com.cts.fse.domain.ViewTask;
import com.cts.fse.service.ViewTaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(ViewTaskController.class)
public class ViewTaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ViewTaskService viewTaskService;

	@Test
	public void testGetAllViewTasks() throws Exception {

		List<ViewTask> viewTasks = new ArrayList<>();

		Task task = new Task();
		task.setTaskName("Task 1");

		ParentTask parentTask = new ParentTask();
		parentTask.setParentTask("Parent Task 1");
		ViewTask viewTask = new ViewTask(task, parentTask);
		viewTasks.add(viewTask);

		when(viewTaskService.getAllViewTasks()).thenReturn(viewTasks);

		MvcResult result = mockMvc.perform(get("/viewtask/allViewTasks")).andDo(print()).andExpect(status().isOk())
				.andReturn();

		assertTrue(HttpStatus.OK.value() == result.getResponse().getStatus());
		assertTrue(result.getResponse().getContentAsString().contains("Task 1"));
		assertTrue(result.getResponse().getContentAsString().contains("Parent Task 1"));

	}

	@Test
	public void testEndTask() throws Exception {

		Task task = new Task();
		task.setTaskName("taskName");

		Optional<Task> optionalTask = Optional.of(task);
		when(viewTaskService.getTaskById(Integer.valueOf(1))).thenReturn(optionalTask);
		doNothing().when(viewTaskService).editTask(task);

		MvcResult result = mockMvc.perform(get("/viewtask/endtask/1")).andReturn();

		assertTrue(HttpStatus.ACCEPTED.value() == result.getResponse().getStatus());

	}

	@Test
	public void testEndTaskByInvalidId() throws Exception {

		Optional<Task> optionalTask = Optional.empty();
		when(viewTaskService.getTaskById(Integer.valueOf(1))).thenReturn(optionalTask);

		MvcResult result = mockMvc.perform(get("/viewtask/endtask/1")).andReturn();

		assertTrue(HttpStatus.BAD_REQUEST.value() == result.getResponse().getStatus());

	}

}
