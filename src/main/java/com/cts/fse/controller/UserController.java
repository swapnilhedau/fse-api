package com.cts.fse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.domain.User;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping("/add")
	public void addUser(@RequestBody User user) {

	}

	@PostMapping("/edit")
	public void editUser(@RequestBody User user) {

	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return null;
	}

	@GetMapping("/{userId}")
	public User getUserById() {
		return null;
	}

	@DeleteMapping("/{userId}")
	public void deleteUser() {

	}

}
