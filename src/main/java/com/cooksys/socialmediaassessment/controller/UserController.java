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

	private UserService userService;
	private UserMapper userMapper;
	private TweetMapper tweetMapper;

	public UserController(UserService userService, UserMapper userMapper, TweetMapper tweetMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
		this.tweetMapper = tweetMapper;
	}

	@GetMapping
	public List<UserResponseDTO> getUsers() {
		return this.userMapper.toResponseDTOs(this.userService.getUsers());
	}

	@PostMapping
	public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
		return this.userMapper.toResponseDTO(this.userService.createUser(this.userMapper.fromRequestDTO(userRequestDTO)));
	}

	@GetMapping("/@{username}")
	public UserResponseDTO getUser(@PathVariable(name = "username") String username) {
		return this.userMapper.toResponseDTO(this.userService.getUser(username));
	}

	//TODO: make sure it ignores null fields
	@PatchMapping("/@{username}")
	public UserResponseDTO updateProfile(@PathVariable(name = "username") String username,
			@RequestBody UserRequestDTO userRequestDTO) {
		return this.userMapper
				.toResponseDTO(this.userService.updateProfile(username, this.userMapper.fromRequestDTO(userRequestDTO)));
	}

	@DeleteMapping("/@{username}")
	public UserResponseDTO deactivateUser(@PathVariable(name = "username") String username,
			@RequestBody Credentials creds) {
		return this.userMapper.toResponseDTO(this.userService.deactivateUser(username, creds.getPassword()));
	}

	@PostMapping("/@{username}/follow")
	public void followUser(@PathVariable(name = "username") String usernameToFollow,
			@RequestBody Credentials followerCreds) {
		this.userService.followUser(usernameToFollow, followerCreds);
	}

	@PostMapping("/@{username}/unfollow")
	public void unfollowUser(@PathVariable(name = "username") String usernameToFollow,
			@RequestBody Credentials followerCreds) {
		this.userService.unfollowUser(usernameToFollow, followerCreds);
	}

	@GetMapping("/@{username}/feed")
	public List<TweetResponseDTO> getFeed(@PathVariable(name = "username") String username) {
		return this.tweetMapper.toResponseDTOs(this.userService.getFeed(username));
	}

	@GetMapping("/@{username}/tweets")
	public List<TweetResponseDTO> getTweets(@PathVariable(name = "username") String username) {
		return this.tweetMapper.toResponseDTOs(this.userService.getTweets(username));
	}

	@GetMapping("/@{username}/mentions")
	public List<TweetResponseDTO> getMentions(@PathVariable(name = "username") String username) {
		return this.tweetMapper.toResponseDTOs(this.userService.getMentions(username));
	}

	@GetMapping("/@{username}/followers")
	public List<UserResponseDTO> getFollowers(@PathVariable(name = "username") String username) {
		return this.userMapper.toResponseDTOs(this.userService.getFollowers(username));
	}

	@GetMapping("/@{username}/following")
	public List<UserResponseDTO> getFollowing(@PathVariable(name = "username") String username) {
		return this.userMapper.toResponseDTOs(this.userService.getFollowing(username));
	}
}
