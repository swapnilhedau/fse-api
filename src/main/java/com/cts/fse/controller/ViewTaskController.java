package com.cts.fse.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.domain.Task;
import com.cts.fse.domain.ViewTask;
import com.cts.fse.service.ViewTaskService;

@CrossOrigin
@RestController
@RequestMapping("/viewtask")
public class ViewTaskController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ViewTaskService viewTaskService;

	public ViewTaskController(ViewTaskService viewTaskService) {
		this.viewTaskService = viewTaskService;
	}

	@GetMapping("/allViewTasks")
	public List<ViewTask> getAllViewTasks() {
		logger.info("-- get all ViewTasks  --");

		return viewTaskService.getAllViewTasks();

	}

	@GetMapping("/endtask/{taskId}")
	public ResponseEntity<Object> endTask(@PathVariable int taskId) {
		logger.info("-- end task with id -- " + taskId);

		Optional<Task> optionalTask = viewTaskService.getTaskById(taskId);

		if (!optionalTask.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		Task toBeEndedTask = optionalTask.get();
		toBeEndedTask.setStatus("CLOSE");

		viewTaskService.editTask(toBeEndedTask);

		return ResponseEntity.accepted().build();
	}

}
