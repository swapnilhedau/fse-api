package com.cts.fse.service;

import java.util.List;
import java.util.Optional;

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

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Optional<Task> getTaskById(Integer taskId) {
		return taskRepository.findById(taskId);
	}

	public void editTask(Task task) {
		taskRepository.save(task);
	}
}
