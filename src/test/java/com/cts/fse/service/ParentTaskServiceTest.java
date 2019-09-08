package com.cts.fse.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.cts.fse.domain.ParentTask;
import com.cts.fse.repository.ParentTaskRepository;

@RunWith(MockitoJUnitRunner.class)
public class ParentTaskServiceTest {

	@InjectMocks
	private ParentTaskService parentTaskService;

	@Mock
	private ParentTaskRepository parentTaskRepository;

	private static final Integer PARENT_ID = Integer.valueOf(1);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(parentTaskService, "parentTaskRepository", parentTaskRepository);
	}

	@Test
	public void testAddParentTask() {
		ParentTask addParentTask = new ParentTask();
		addParentTask.setParentTask("parentTask");

		when(parentTaskRepository.save(addParentTask)).thenReturn(addParentTask);

		ParentTask addedParentTask = parentTaskService.addParentTask(addParentTask);

		assertNotNull(addedParentTask);
		assertTrue(addedParentTask.getParentTask().equalsIgnoreCase(addedParentTask.getParentTask()));

	}

	@Test
	public void testEditParentTask() {

		ParentTask editParentTask = new ParentTask();
		editParentTask.setParentId(PARENT_ID);
		editParentTask.setParentTask("parentTask");
		when(parentTaskRepository.save(editParentTask)).thenReturn(editParentTask);

		parentTaskService.editParentTask(editParentTask);

	}

	@Test
	public void testGetAllParentTasks() {
		List<ParentTask> parentTasks = new ArrayList<>();

		ParentTask parentTask1 = new ParentTask();
		ParentTask parentTask2 = new ParentTask();

		parentTasks.add(parentTask1);
		parentTasks.add(parentTask2);

		when(parentTaskRepository.findAll()).thenReturn(parentTasks);

		List<ParentTask> allParentTasks = parentTaskService.getAllParentTasks();

		assertNotNull(allParentTasks);
		assertEquals(2, allParentTasks.size());

	}

	@Test
	public void testGetParentTaskById() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTask("parentTask");

		Optional<ParentTask> optionalParentTask = Optional.of(parentTask);
		when(parentTaskRepository.findById(PARENT_ID)).thenReturn(optionalParentTask);

		Optional<ParentTask> parentTaskById = parentTaskService.getParentTaskById(PARENT_ID);
		assertNotNull(parentTaskById);
		if (parentTaskById.isPresent()) {
			assertEquals(parentTaskById.get().getParentTask(), parentTask.getParentTask());
		}
	}

}
