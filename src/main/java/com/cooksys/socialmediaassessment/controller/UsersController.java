package com.cooksys.socialmediaassessment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassessment.dto.UsersRequestDTO;
import com.cooksys.socialmediaassessment.dto.UsersResponseDTO;
import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.entity.Users;
import com.cooksys.socialmediaassessment.mapper.UsersMapper;
import com.cooksys.socialmediaassessment.service.UsersService;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	private UsersService uService;
	private UsersMapper uMapper;

	public UsersController (UsersService uService, UsersMapper uMapper) {
		this.uService = uService;
		this.uMapper = uMapper;
	}

	@GetMapping
	public List<UsersResponseDTO> getUsers() {
		return (List<UsersResponseDTO>) this.uMapper.toResponseDTOs(this.uService.getUsers());
	}

	@PostMapping
	public UsersResponseDTO createUser(UsersRequestDTO userRequestDTO) {
		return this.uMapper.toResponseDTO(this.uService.createUser(this.uMapper.fromRequestDTO(userRequestDTO)));
	}

	@GetMapping("/@{username}")
	public UsersResponseDTO getUser(@PathVariable(name = "username") String username) {
		return this.uMapper.toResponseDTO(this.uService.getUserByName(username));
	}

	@PatchMapping("/@{username}")
	public UsersResponseDTO updateProfile(@PathVariable(name = "username") String username, UsersRequestDTO userRequestDTO) {
		return this.uMapper.toResponseDTO(this.uService.updateProfile(username, this.uMapper.fromRequestDTO(userRequestDTO)));
	}
}
