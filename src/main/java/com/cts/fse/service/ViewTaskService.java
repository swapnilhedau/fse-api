package com.cts.fse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.fse.domain.ParentTask;
import com.cts.fse.domain.Task;
import com.cts.fse.domain.ViewTask;

@Service
public class ViewTaskService {

	private TaskService taskService;
	private ParentTaskService parentTaskService;

	public ViewTaskService(TaskService taskService, ParentTaskService parentTaskService) {
		this.taskService = taskService;
		this.parentTaskService = parentTaskService;
	}

	public List<ViewTask> getAllViewTasks() {
		List<ViewTask> viewTasks = new ArrayList<>();

		List<Task> tasks = taskService.getAllTasks();
		List<ParentTask> parentTasks = parentTaskService.getAllParentTasks();

		Map<Integer, ParentTask> parentTaskKeyMap = new HashMap<>();
		parentTasks.stream().forEach(t -> parentTaskKeyMap.put(t.getParentId(), t));

		ParentTask defaultParentTask = new ParentTask();
		defaultParentTask.setParentTask("This task has no parent.");
		tasks.stream().forEach(
				t -> viewTasks.add(new ViewTask(t, parentTaskKeyMap.getOrDefault(t.getParentId(), defaultParentTask))));

		return viewTasks;
	}

	public Optional<Task> getTaskById(int taskId) {
		return taskService.getTaskById(taskId);
	}

	public void editTask(Task task) {
		taskService.editTask(task);
	}

}
