package com.cts.fse.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.domain.User;
import com.cts.fse.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		logger.info("-- add user --");

		if (ObjectUtils.isEmpty(user.getFirstName()) || ObjectUtils.isEmpty(user.getLastName())
				|| ObjectUtils.isEmpty(user.getEmployeeId())) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));

	}

	@PostMapping("/edit/{userId}")
	public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable int userId) {
		logger.info("-- edit user --");

		Optional<User> userOptional = userService.getUserById(userId);

		if (!userOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		if (ObjectUtils.isEmpty(user.getFirstName()) || ObjectUtils.isEmpty(user.getLastName())
				|| ObjectUtils.isEmpty(user.getEmployeeId())) {
			return ResponseEntity.notFound().build();
		}

		user.setUserId(Integer.valueOf(userId));
		userService.editUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(user);

	}

	@GetMapping("/allUsers")
	public List<User> getAllUsers() {
		logger.info("-- get all Users  --");

		return userService.getAllUsers();

	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		logger.info("-- get user by id --" + userId);

		Optional<User> user = userService.getUserById(userId);

		if (!user.isPresent())
			return ResponseEntity.badRequest().build();

		return ResponseEntity.ok(user.get());

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable int userId) {
		logger.info("-- delete user by id --" + userId);

		Optional<User> optionalUser = userService.getUserById(userId);

		if (!optionalUser.isPresent())
			return ResponseEntity.badRequest().build();

		userService.deleteUser(userId);
		return ResponseEntity.accepted().body(optionalUser.get());

	}

}
