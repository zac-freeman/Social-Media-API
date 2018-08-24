package com.cooksys.socialmediaassessment.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.entity.User;
import com.cooksys.socialmediaassessment.repository.TweetRepository;
import com.cooksys.socialmediaassessment.repository.UserRepository;

@Service
public class TweetService {

	private TweetRepository tRepo;
	private UserRepository uRepo;

	public TweetService(TweetRepository tRepo, UserRepository uRepo) {
		this.tRepo = tRepo;
		this.uRepo = uRepo;
	}

	public List<Tweet> getTweets() {
		return this.tRepo.findAll();
	}

	public Tweet createTweet(String content, Credentials credentials) {
		User author = this.uRepo.findUserByCredentials(credentials);
		Tweet tweet = new Tweet();
		tweet.setContent(content);
		tweet.setAuthor(author);
		tweet.setPosted(new Timestamp(Instant.now().toEpochMilli()));
		tweet.setVisible(true);
		//setMentionedUser
		//setHashtags

		author.getTweets().add(this.tRepo.save(tweet));
		this.uRepo.save(author);
		return tweet;
	}
}
