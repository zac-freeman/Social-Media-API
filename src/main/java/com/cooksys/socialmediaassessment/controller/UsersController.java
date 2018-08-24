package com.cooksys.socialmediaassessment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassessment.dto.UserRequestDTO;
import com.cooksys.socialmediaassessment.dto.UserResponseDTO;
import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.mapper.UserMapper;
import com.cooksys.socialmediaassessment.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	private UserService uService;
	private UserMapper uMapper;

	public UsersController (UserService uService, UserMapper uMapper) {
		this.uService = uService;
		this.uMapper = uMapper;
	}

	@GetMapping
	public List<UserResponseDTO> getUsers() {
		return (List<UserResponseDTO>) this.uMapper.toResponseDTOs(this.uService.getUsers());
	}

	@PostMapping
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		return this.uMapper.toResponseDTO(this.uService.createUser(this.uMapper.fromRequestDTO(userRequestDTO)));
	}

	@GetMapping("/@{username}")
	public UserResponseDTO getUser(@PathVariable(name = "username") String username) {
		return this.uMapper.toResponseDTO(this.uService.getUserByName(username));
	}

	//TODO: ask how to pass this as a single object
	@PatchMapping("/@{username}")
	public UserResponseDTO updateProfile(@PathVariable(name = "username") String username, UserRequestDTO userRequestDTO) {
		return this.uMapper.toResponseDTO(this.uService.updateProfile(username, this.uMapper.fromRequestDTO(userRequestDTO)));
	}

	//TODO: ask how to pass this as a single object, should I just take a Credentials object as a parameter
	@DeleteMapping("/@{username}")
	public UserResponseDTO deactivateUser(@PathVariable(name = "username") String username, String password) {
		return this.uMapper.toResponseDTO(this.uService.deactivateUser(username, password));
	}

	@PostMapping("/@{username}/follow")
	public void followUser(@PathVariable(name = "username") String usernameToFollow, Credentials followerCreds) {
		this.uService.followUser(usernameToFollow, followerCreds);
	}
}
