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

import com.cts.fse.domain.ParentTask;
import com.cts.fse.domain.Task;
import com.cts.fse.service.ParentTaskService;
import com.cts.fse.service.TaskService;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private ParentTaskService parentTaskService;

	private TaskService taskService;

	public TaskController(ParentTaskService parentTaskService, TaskService taskService) {
		this.parentTaskService = parentTaskService;
		this.taskService = taskService;
	}

	@GetMapping("/allParentTasks")
	public List<ParentTask> getAllParentTask() {
		logger.info("-- get all Parent Tasks  --");

		return parentTaskService.getAllParentTasks();

	}

	@PostMapping("/addParentTask")
	public ResponseEntity<ParentTask> addParentTask(@RequestBody ParentTask parentTask) {
		logger.info("-- add parent task --");

		ParentTask savedParentTask = parentTaskService.addParentTask(parentTask);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedParentTask);
	}

	@PostMapping("/editParentTask/{parentTask}")
	public ResponseEntity<ParentTask> editParentTask(@RequestBody ParentTask parentTask,
			@PathVariable int parentTaskId) {
		logger.info("-- edit task --");

		Optional<ParentTask> taskOptional = parentTaskService.getParentTaskById(parentTaskId);

		if (!taskOptional.isPresent())
			return ResponseEntity.notFound().build();

		parentTask.setParentId(Integer.valueOf(parentTaskId));
		parentTaskService.editParentTask(parentTask);

		return ResponseEntity.status(HttpStatus.CREATED).body(parentTask);
	}

	@PostMapping("/addTask")
	public ResponseEntity<Task> addTask(@RequestBody Task task) {
		logger.info("-- add task --");

		Task savedTask = taskService.addTask(task);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
	}

	@PostMapping("/editTask/{taskId}")
	public ResponseEntity<Task> editTask(@RequestBody Task task, @PathVariable int taskId) {
		logger.info("-- edit task --");

		Optional<Task> taskOptional = taskService.getTaskById(taskId);

		if (!taskOptional.isPresent())
			return ResponseEntity.notFound().build();

		task.setProjectId(Integer.valueOf(taskId));
		taskService.editTask(task);

		return ResponseEntity.status(HttpStatus.CREATED).body(task);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable int taskId) {
		logger.info("-- get task by id --" + taskId);

		Optional<Task> optionalTask = taskService.getTaskById(taskId);

		if (!optionalTask.isPresent())
			return ResponseEntity.badRequest().build();

		return ResponseEntity.ok(optionalTask.get());

	}

}
