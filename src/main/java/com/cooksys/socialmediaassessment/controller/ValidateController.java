package com.cooksys.socialmediaassessment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassessment.service.TagService;
import com.cooksys.socialmediaassessment.service.UserService;

@RestController
@RequestMapping(value = "/validate")
public class ValidateController {

	private TagService tagService;
	private UserService userService;

	public ValidateController(TagService tagService, UserService userService) {
		this.tagService = tagService;
		this.userService = userService;
	}

	@GetMapping("/tag/exists/{label}")
	public boolean tagExists(@PathVariable(name = "label") String label) {
		return this.tagService.exists(label);
	}

	@GetMapping("/tag/exists/@{username}")
	public boolean userExists(@PathVariable(name = "username") String username) {
		return this.userService.exists(username);
	}

	@GetMapping("/username/available/@{username}")
	public boolean usernameAvailable(@PathVariable(name = "username") String username) {
		return !this.userService.exists(username);
	}
}
