package com.cooksys.socialmediaassessment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassessment.dto.TweetResponseDTO;
import com.cooksys.socialmediaassessment.dto.UserRequestDTO;
import com.cooksys.socialmediaassessment.dto.UserResponseDTO;
import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.mapper.TweetMapper;
import com.cooksys.socialmediaassessment.mapper.UserMapper;
import com.cooksys.socialmediaassessment.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private UserService uService;
	private UserMapper uMapper;
	private TweetMapper tMapper;

	public UserController(UserService uService, UserMapper uMapper, TweetMapper tMapper) {
		this.uService = uService;
		this.uMapper = uMapper;
		this.tMapper = tMapper;
	}

	@GetMapping
	public List<UserResponseDTO> getUsers() {
		return this.uMapper.toResponseDTOs(this.uService.getUsers());
	}

	@PostMapping
	public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
		return this.uMapper.toResponseDTO(this.uService.createUser(this.uMapper.fromRequestDTO(userRequestDTO)));
	}

	@GetMapping("/@{username}")
	public UserResponseDTO getUser(@PathVariable(name = "username") String username) {
		return this.uMapper.toResponseDTO(this.uService.getUser(username));
	}

	@PatchMapping("/@{username}")
	public UserResponseDTO updateProfile(@PathVariable(name = "username") String username,
			@RequestBody UserRequestDTO userRequestDTO) {
		return this.uMapper
				.toResponseDTO(this.uService.updateProfile(username, this.uMapper.fromRequestDTO(userRequestDTO)));
	}

	@DeleteMapping("/@{username}")
	public UserResponseDTO deactivateUser(@PathVariable(name = "username") String username,
			@RequestBody Credentials creds) {
		return this.uMapper.toResponseDTO(this.uService.deactivateUser(username, creds.getPassword()));
	}

	@PostMapping("/@{username}/follow")
	public void followUser(@PathVariable(name = "username") String usernameToFollow,
			@RequestBody Credentials followerCreds) {
		this.uService.followUser(usernameToFollow, followerCreds);
	}

	@PostMapping("/@{username}/unfollow")
	public void unfollowUser(@PathVariable(name = "username") String usernameToFollow,
			@RequestBody Credentials followerCreds) {
		this.uService.unfollowUser(usernameToFollow, followerCreds);
	}

	@GetMapping("/@{username}/feed")
	public List<TweetResponseDTO> getFeed(@PathVariable(name = "username") String username) {
		return this.tMapper.toResponseDTOs(this.uService.getFeed(username));
	}

	@GetMapping("/@{username}/tweets")
	public List<TweetResponseDTO> getTweets(@PathVariable(name = "username") String username) {
		return this.tMapper.toResponseDTOs(this.uService.getTweets(username));
	}

	@GetMapping("/@{username}/mentions")
	public List<TweetResponseDTO> getMentions(@PathVariable(name = "username") String username) {
		return this.tMapper.toResponseDTOs(this.uService.getMentions(username));
	}

	@GetMapping("/@{username}/followers")
	public List<UserResponseDTO> getFollowers(@PathVariable(name = "username") String username) {
		return this.uMapper.toResponseDTOs(this.uService.getFollowers(username));
	}

	@GetMapping("/@{username}/following")
	public List<UserResponseDTO> getFollowing(@PathVariable(name = "username") String username) {
		return this.uMapper.toResponseDTOs(this.uService.getFollowing(username));
	}
}
