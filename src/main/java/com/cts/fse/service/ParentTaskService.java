package com.cts.fse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.fse.domain.ParentTask;
import com.cts.fse.repository.ParentTaskRepository;

@Service
public class ParentTaskService {

	private final ParentTaskRepository parentTaskRepository;

	public ParentTaskService(ParentTaskRepository parentTaskRepository) {
		this.parentTaskRepository = parentTaskRepository;
	}

	public ParentTask addParentTask(ParentTask parentTask) {
		return parentTaskRepository.save(parentTask);
	}

	public List<ParentTask> getAllParentTasks() {
		return parentTaskRepository.findAll();
	}

}
