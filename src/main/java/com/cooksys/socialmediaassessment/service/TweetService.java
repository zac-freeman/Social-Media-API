package com.cooksys.socialmediaassessment.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassessment.dto.TweetRequestDTO;
import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.entity.User;
import com.cooksys.socialmediaassessment.repository.TweetRepository;
import com.cooksys.socialmediaassessment.repository.UserRepository;

//TODO: ADD EXCEPTIONS
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

	public Tweet createTweet(String content, Credentials creds) {
		User author = this.uRepo.findUserByCredentials(creds);
		Tweet tweet = new Tweet();
		tweet.setContent(content);
		tweet.setAuthor(author);
		tweet.setPosted(new Timestamp(Instant.now().toEpochMilli()));
		tweet.setVisible(true);
		// TODO: setMentionedUser
		// TODO: setHashtags

		author.getTweets().add(this.tRepo.save(tweet));
		this.uRepo.save(author);
		return tweet;
	}

	public Tweet hideTweet(Tweet tweet, Credentials creds) {
		User author = this.uRepo.findUserByCredentials(creds);
		if (author != null) {
			tweet.setVisible(false);
			return this.tRepo.save(tweet);
		}
		return null;
	}

	public void likeTweet(Tweet tweet, Credentials creds) {
		User liker = this.uRepo.findUserByCredentials(creds);
		if (liker != null) {
			liker.getLikedTweets().add(tweet);
			tweet.getLikes().add(liker);
			this.tRepo.save(tweet);
			this.uRepo.save(liker);
		}
	}

	public Tweet replyToTweet(Tweet tweet, TweetRequestDTO reply) {
		Tweet replyEntity = this.createTweet(reply.getContent(), reply.getCredentials());
		replyEntity.setInReplyTo(tweet);
		tweet.getReplies().add(replyEntity);
		this.tRepo.save(tweet);
		return this.tRepo.save(replyEntity);
	}

	public Tweet repostTweet(Tweet tweet, Credentials reposter) {
		Tweet repost = this.createTweet(null, reposter);
		repost.setRepostOf(tweet);
		tweet.getReposts().add(repost);
		this.tRepo.save(tweet);
		return this.tRepo.save(repost);
	}

	// TODO: parsing function
}
