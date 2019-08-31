package com.cts.fse.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cts.fse.domain.User;
import com.cts.fse.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void testAddUser() throws Exception {

		User addUser = new User();
		addUser.setUserId(Integer.valueOf(1));
		addUser.setFirstName("James");
		addUser.setLastName("Bond");
		addUser.setEmployeeId(12345);
		when(userService.addUser(addUser)).thenReturn(addUser);

		String exampleUser = "{\"userId\":1,\"firstName\":\"James\",\"lastName\":\"Bond\",\"employeeId\":12345,\"projectId\":null,\"taskId\":null}";

		MvcResult result = mockMvc.perform(post("/user/add").accept(MediaType.APPLICATION_JSON).content(exampleUser)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();

		assertTrue(HttpStatus.CREATED.value() == result.getResponse().getStatus());

	}

	@Test
	public void testEditUser() throws Exception {

		User editUser = new User();
		editUser.setUserId(Integer.valueOf(1));
		editUser.setFirstName("James");
		editUser.setLastName("Bond");
		editUser.setEmployeeId(12345);

		Optional<User> optionalUser = Optional.of(editUser);
		when(userService.getUserById(Integer.valueOf(1))).thenReturn(optionalUser);

		doNothing().when(userService).editUser(editUser);

		String exampleUser = "{\"userId\":1,\"firstName\":\"James\",\"lastName\":\"Bond\",\"employeeId\":12345,\"projectId\":null,\"taskId\":null}";

		MvcResult result = mockMvc.perform(post("/user/edit/1").accept(MediaType.APPLICATION_JSON).content(exampleUser)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();

		assertTrue(HttpStatus.CREATED.value() == result.getResponse().getStatus());
		assertEquals(exampleUser, result.getResponse().getContentAsString());

	}

	@Test
	public void testEditUserWithInvalidId() throws Exception {

		Optional<User> optionalUser = Optional.empty();
		when(userService.getUserById(Integer.valueOf(1))).thenReturn(optionalUser);

		String exampleUser = "{\"userId\":1,\"firstName\":\"James\",\"lastName\":\"Bond\",\"employeeId\":12345,\"projectId\":null,\"taskId\":null}";

		MvcResult result = mockMvc.perform(post("/user/edit/99").accept(MediaType.APPLICATION_JSON).content(exampleUser)
				.contentType(MediaType.APPLICATION_JSON)).andReturn();

		assertTrue(HttpStatus.NOT_FOUND.value() == result.getResponse().getStatus());

	}

	@Test
	public void testGetAllUsers() throws Exception {

		List<User> users = Lists.newArrayList();
		User user = new User();
		user.setFirstName("James");
		users.add(user);

		when(userService.getAllUsers()).thenReturn(users);

		MvcResult result = mockMvc.perform(get("/user/allUsers")).andDo(print()).andExpect(status().isOk()).andReturn();

		assertTrue(HttpStatus.OK.value() == result.getResponse().getStatus());
		assertTrue(result.getResponse().getContentAsString().contains("James"));
	}

	@Test
	public void testGetUserById() throws Exception {

		User user = new User();
		user.setUserId(Integer.valueOf(1));
		user.setFirstName("James");
		user.setLastName("Bond");
		user.setEmployeeId(12345);

		Optional<User> optionalUser = Optional.of(user);
		when(userService.getUserById(Integer.valueOf(1))).thenReturn(optionalUser);

		String userByIdString = "{\"userId\":1,\"firstName\":\"James\",\"lastName\":\"Bond\",\"employeeId\":12345,\"projectId\":null,\"taskId\":null}";

		MvcResult result = mockMvc.perform(get("/user/1")).andDo(print()).andReturn();

		assertTrue(HttpStatus.OK.value() == result.getResponse().getStatus());
		assertEquals(userByIdString, result.getResponse().getContentAsString());

	}

	@Test
	public void testGetUserByIdWithInvalidId() throws Exception {

		Optional<User> optionalUser = Optional.empty();
		when(userService.getUserById(Integer.valueOf(1))).thenReturn(optionalUser);

		MvcResult result = mockMvc.perform(get("/user/99")).andDo(print()).andReturn();

		assertTrue(HttpStatus.BAD_REQUEST.value() == result.getResponse().getStatus());

	}

	@Test
	public void testDeleteUser() throws Exception {

		User user = new User();
		user.setUserId(Integer.valueOf(1));
		user.setFirstName("James");
		user.setLastName("Bond");
		user.setEmployeeId(12345);

		Optional<User> optionalUser = Optional.of(user);
		when(userService.getUserById(Integer.valueOf(1))).thenReturn(optionalUser);

		doNothing().when(userService).deleteUser(Integer.valueOf(1));

		MvcResult result = mockMvc.perform(delete("/user/1")).andDo(print()).andExpect(status().isAccepted())
				.andReturn();

		assertTrue(HttpStatus.ACCEPTED.value() == result.getResponse().getStatus());

	}

	@Test
	public void testDeleteUserWithInvalidId() throws Exception {

		doNothing().when(userService).deleteUser(Integer.valueOf(1));

		MvcResult result = mockMvc.perform(delete("/user/99")).andDo(print()).andExpect(status().isBadRequest())
				.andReturn();

		assertTrue(HttpStatus.BAD_REQUEST.value() == result.getResponse().getStatus());
	}
}
