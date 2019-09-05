package com.cts.fse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
