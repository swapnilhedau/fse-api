package com.cts.fse.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.cts.fse.domain.User;
import com.cts.fse.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	private static final Integer USER_ID = Integer.valueOf(1);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(userService, "userRepository", userRepository);
	}

	@Test
	public void testAddUser() {

		User addUser = new User();
		addUser.setUserId(USER_ID);
		addUser.setFirstName("James");
		addUser.setLastName("Bond");
		addUser.setEmployeeId(12345);
		when(userRepository.save(addUser)).thenReturn(addUser);

		User addedUser = userService.addUser(addUser);

		assertNotNull(addedUser);
		assertTrue(addedUser.getFirstName().equals(addedUser.getFirstName()));
	}

	@Test
	public void testEditUSer() {

		User editUser = new User();
		editUser.setFirstName("James");
		when(userRepository.save(editUser)).thenReturn(editUser);

		userService.editUser(editUser);

	}

	@Test
	public void testGetAllUsers() {

		List<User> listOfAllUsers = Lists.newArrayList();
		User user1 = new User();

		User user2 = new User();

		listOfAllUsers.add(user1);
		listOfAllUsers.add(user2);

		when(userService.getAllUsers()).thenReturn(listOfAllUsers);

		List<User> allUsers = userService.getAllUsers();

		assertNotNull(allUsers);
		assertEquals(2, allUsers.size());

	}

	@Test
	public void testGetUserById() {

		User user = new User();
		user.setFirstName("Bond");
		Optional<User> optionalUser = Optional.of(user);
		when(userService.getUserById(USER_ID)).thenReturn(optionalUser);

		Optional<User> userById = userService.getUserById(USER_ID);
		assertNotNull(userById);
		if (userById.isPresent()) {
			assertEquals(userById.get().getFirstName(), user.getFirstName());
		}

	}

	@Test
	public void testDeleteUser() {

		doNothing().when(userRepository).deleteById(USER_ID);
		userService.deleteUser(USER_ID);

	}

}
