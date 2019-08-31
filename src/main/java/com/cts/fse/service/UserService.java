package com.cts.fse.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.fse.domain.User;
import com.cts.fse.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public void editUser(User user) {
		userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public List<String> getAllUserNames() {
		return userRepository.findAll().stream().map(user -> user.getFirstName() + " " + user.getLastName())
				.collect(Collectors.toList());
	}

	public Optional<User> getUserById(Integer userId) {
		return userRepository.findById(userId);
	}

	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);
	}

}
