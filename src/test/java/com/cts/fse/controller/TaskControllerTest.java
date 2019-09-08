package com.cts.fse.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import com.cts.fse.service.ParentTaskService;
import com.cts.fse.service.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskService taskService;

	@MockBean
	private ParentTaskService parentTaskService;

	@Test
	public void testGetAllParentTask() throws Exception {

		List<ParentTask> parentTasks = new ArrayList<>();
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		parentTask.setParentTask("parentTask");
		parentTasks.add(parentTask);

		when(parentTaskService.getAllParentTasks()).thenReturn(parentTasks);

		MvcResult result = mockMvc.perform(get("/task/allParentTasks")).andDo(print()).andExpect(status().isOk())
				.andReturn();

		assertTrue(HttpStatus.OK.value() == result.getResponse().getStatus());
		assertTrue(
				result.getResponse().getContentAsString().contains("{\"parentId\":1,\"parentTask\":\"parentTask\"}"));

	}

	/*
	 * 
	 * @Test public void testAddParentTask() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testEditParentTask() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testAddTask() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testEditTask() { fail("Not yet implemented"); }
	 * 
	 */

	@Test
	public void testGetTaskById() throws Exception {

		Task task = new Task();
		task.setTaskId(1);
		task.setTaskName("Task 1");

		Optional<Task> optionalTask = Optional.of(task);
		when(taskService.getTaskById(Integer.valueOf(1))).thenReturn(optionalTask);

		String taskByIdString = "{\"taskId\":1,\"parentId\":null,\"projectId\":null,\"taskName\":\"Task 1\",\"startDate\":null,\"endDate\":null,\"priority\":null,\"status\":null}";

		MvcResult result = mockMvc.perform(get("/task/1")).andDo(print()).andReturn();

		assertTrue(HttpStatus.OK.value() == result.getResponse().getStatus());
		assertEquals(taskByIdString, result.getResponse().getContentAsString());

	}

}
