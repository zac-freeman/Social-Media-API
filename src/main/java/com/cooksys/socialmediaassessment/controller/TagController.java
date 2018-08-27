package com.cooksys.socialmediaassessment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassessment.dto.TagResponseDTO;
import com.cooksys.socialmediaassessment.dto.TweetResponseDTO;
import com.cooksys.socialmediaassessment.mapper.TagMapper;
import com.cooksys.socialmediaassessment.mapper.TweetMapper;
import com.cooksys.socialmediaassessment.service.TagService;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

	private TagService tagService;
	private TagMapper tagMapper;
	private TweetMapper tweetMapper;

	public TagController(TagService tagService, TagMapper tagMapper, TweetMapper tweetMapper) {
		this.tagService = tagService;
		this.tagMapper = tagMapper;
		this.tweetMapper = tweetMapper;
	}

	@GetMapping
	public List<TagResponseDTO> getTags() {
		return this.tagMapper.toResponseDTOs(this.tagService.getTags());
	}

	@GetMapping("/{label}")
	public List<TweetResponseDTO> getTweets(@PathVariable(name = "label") String label) {
		return this.tweetMapper.toResponseDTOs(this.tagService.getTweets(label));
	}
}
