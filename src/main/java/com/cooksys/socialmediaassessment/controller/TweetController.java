package com.cooksys.socialmediaassessment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassessment.dto.TweetRequestDTO;
import com.cooksys.socialmediaassessment.dto.TweetResponseDTO;
import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.mapper.TweetMapper;
import com.cooksys.socialmediaassessment.service.TweetService;

@RestController
@RequestMapping(value = "/tweets")
public class TweetController {

	private TweetService tService;
	private TweetMapper tMapper;

	public TweetController(TweetService tService, TweetMapper tMapper) {
		this.tService = tService;
		this.tMapper = tMapper;
	}

	@GetMapping
	public List<TweetResponseDTO> getTweets() {
		return (List<TweetResponseDTO>) this.tMapper.toResponseDTOs(this.tService.getTweets());
	}

	@PostMapping
	public TweetResponseDTO createTweet(@RequestBody TweetRequestDTO tweet) {
		return this.tMapper.toResponseDTO(this.tService.createTweet(tweet.getContent(), tweet.getCredentials()));
	}

	@GetMapping("/{id}")
	public TweetResponseDTO getTweet(@PathVariable(name = "id") Tweet tweet) {
		return this.tMapper.toResponseDTO(tweet);
	}

	@DeleteMapping("/{id}")
	public TweetResponseDTO hideTweet(@PathVariable(name = "id") Tweet tweet, @RequestBody Credentials credentials) {
		return this.tMapper.toResponseDTO(this.tService.hideTweet(tweet, credentials));
	}
}
