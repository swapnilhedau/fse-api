package com.cts.fse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
