package com.cts.fse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
