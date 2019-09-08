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

import com.cts.fse.domain.Task;
import com.cts.fse.repository.TaskRepository;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

	@InjectMocks
	private TaskService taskService;

	@Mock
	private TaskRepository taskRepository;

	private static final Integer ID = Integer.valueOf(1);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(taskService, "taskRepository", taskRepository);
	}

	@Test
	public void testAddTask() {

		Task addTask = new Task();
		addTask.setTaskName("taskName");
		addTask.setStatus("OPEN");
		addTask.setStartDate(new Date());
		addTask.setEndDate(new Date());
		addTask.setProjectId(ID);
		addTask.setParentId(ID);
		addTask.setPriority(ID);

		when(taskRepository.save(addTask)).thenReturn(addTask);

		Task addedTask = taskService.addTask(addTask);

		assertNotNull(addedTask);
		assertTrue(addTask.getTaskName().equalsIgnoreCase(addedTask.getTaskName()));
		assertTrue(addTask.getStatus().equalsIgnoreCase(addedTask.getStatus()));
		assertTrue(addTask.getStartDate().equals(addedTask.getStartDate()));
		assertTrue(addTask.getEndDate().equals(addedTask.getEndDate()));
		assertTrue(addTask.getProjectId().equals(addedTask.getProjectId()));
		assertTrue(addTask.getParentId().equals(addedTask.getParentId()));
		assertTrue(addTask.getPriority().equals(addedTask.getPriority()));

	}

	@Test
	public void testGetAllTasks() {
		List<Task> tasks = new ArrayList<>();

		Task task1 = new Task();
		Task task2 = new Task();

		tasks.add(task1);
		tasks.add(task2);

		when(taskRepository.findAll()).thenReturn(tasks);

		List<Task> allTasks = taskService.getAllTasks();

		assertNotNull(allTasks);
		assertEquals(2, allTasks.size());

	}

	@Test
	public void testGetTaskById() {

		Task task = new Task();
		task.setTaskName("taskName");

		Optional<Task> optionalTask = Optional.of(task);
		when(taskRepository.findById(ID)).thenReturn(optionalTask);

		Optional<Task> taskById = taskService.getTaskById(ID);
		assertNotNull(taskById);
		if (taskById.isPresent()) {
			assertEquals(taskById.get().getTaskName(), task.getTaskName());
		}
	}

	@Test
	public void testEditTask() {
		Task editParentTask = new Task();
		editParentTask.setTaskName("taskName");
		editParentTask.setStartDate(new Date());
		editParentTask.setEndDate(new Date());
		editParentTask.setProjectId(ID);
		editParentTask.setParentId(ID);
		editParentTask.setPriority(ID);
		when(taskRepository.save(editParentTask)).thenReturn(editParentTask);

		taskService.editTask(editParentTask);

	}

}
