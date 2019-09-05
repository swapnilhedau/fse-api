package com.cts.fse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.domain.ParentTask;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Integer> {

}
