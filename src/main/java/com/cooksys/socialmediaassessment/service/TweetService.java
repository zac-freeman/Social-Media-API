package com.cooksys.socialmediaassessment.service;

import java.util.List;

import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.repository.TweetRepository;

public class TweetService {

	private TweetRepository tRepo;

	public TweetService(TweetRepository tRepo) {
		this.tRepo = tRepo;
	}

	public List<Tweet> getTweets() {
		return this.tRepo.findAll();
	}

	public Tweet createTweet(Tweet tweet) {
		return this.tRepo.save(tweet);	//TODO: find out if this updates join table with User on its own
	}

}
