package com.cts.fse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.fse.domain.Task;
import com.cts.fse.repository.TaskRepository;

@Service
public class TaskService {

	private TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public Task addTask(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> getAllParentTasks() {
		return taskRepository.findAll();
	}
}
