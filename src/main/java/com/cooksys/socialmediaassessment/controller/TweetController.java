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
import com.cooksys.socialmediaassessment.dto.UserResponseDTO;
import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.entity.Tag;
import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.entity.User;
import com.cooksys.socialmediaassessment.mapper.TweetMapper;
import com.cooksys.socialmediaassessment.mapper.UserMapper;
import com.cooksys.socialmediaassessment.service.TweetService;

@RestController
@RequestMapping(value = "/tweets")
public class TweetController {

	private TweetService tService;
	private TweetMapper tMapper;
	private UserMapper uMapper;

	public TweetController(TweetService tService, TweetMapper tMapper, UserMapper uMapper) {
		this.tService = tService;
		this.tMapper = tMapper;
		this.uMapper = uMapper;
	}

	@GetMapping
	public List<TweetResponseDTO> getTweets() {
		return (List<TweetResponseDTO>) this.tMapper.toResponseDTOs(this.tService.getTweets());
	}

	@PostMapping
	public TweetResponseDTO createTweet(@RequestBody TweetRequestDTO tweet) {
		return this.tMapper.toResponseDTO(this.tService.createTweet(tweet.getContent(), tweet.getCredentials()));
	}

	//TODO: check if tweet is hidden
	@GetMapping("/{id}")
	public TweetResponseDTO getTweet(@PathVariable(name = "id") Tweet tweet) {
		return this.tMapper.toResponseDTO(tweet);
	}

	@DeleteMapping("/{id}")
	public TweetResponseDTO hideTweet(@PathVariable(name = "id") Tweet tweet, @RequestBody Credentials creds) {
		return this.tMapper.toResponseDTO(this.tService.hideTweet(tweet, creds));
	}

	@PostMapping("/{id}/like")
	public void likeTweet(@PathVariable(name = "id") Tweet tweet, @RequestBody Credentials creds) {
		this.tService.likeTweet(tweet, creds);
	}

	@PostMapping("/{id}/reply")
	public TweetResponseDTO replyToTweet(@PathVariable(name = "id") Tweet tweet, @RequestBody TweetRequestDTO reply) {
		return this.tMapper.toResponseDTO(this.tService.replyToTweet(tweet, reply));
	}

	@PostMapping("/{id}/repost")
	public TweetResponseDTO repostTweet(@PathVariable(name = "id") Tweet tweet, @RequestBody Credentials reposter) {
		return this.tMapper.toResponseDTO(this.tService.repostTweet(tweet, reposter));
	}

	//TODO: convert Tag entity to DTO
	@GetMapping("/{id}/tags")
	public List<Tag> getHashtags(@PathVariable(name = "id") Tweet tweet) {
		return (List<Tag>) tweet.getHashtags();
	}

	@GetMapping("/{id}/likes")
	public List<UserResponseDTO> getLikes(@PathVariable(name = "id") Tweet tweet) {
		return (List<UserResponseDTO>) this.uMapper.toResponseDTOs(tweet.getLikes());
	}

	//TODO: getContext()

	@GetMapping("/{id}/replies")
	public List<TweetResponseDTO> getDirectReplies(@PathVariable(name = "id") Tweet tweet) {
		return (List<TweetResponseDTO>) this.tMapper.toResponseDTOs(tweet.getReplies());
	}

	@GetMapping("/{id}/reposts")
	public List<TweetResponseDTO> getDirectReposts(@PathVariable(name = "id") Tweet tweet) {
		return (List<TweetResponseDTO>) this.tMapper.toResponseDTOs(tweet.getReposts());
	}

	@GetMapping("/{id}/mentions")
	public List<UserResponseDTO> getMentions(@PathVariable(name = "id") Tweet tweet) {
		return (List<UserResponseDTO>) this.uMapper.toResponseDTOs(tweet.getMentionedUsers());
	}
}
