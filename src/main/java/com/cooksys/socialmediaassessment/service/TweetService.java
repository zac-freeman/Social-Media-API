package com.cooksys.socialmediaassessment.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassessment.dto.TweetRequestDTO;
import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.entity.Tag;
import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.entity.User;
import com.cooksys.socialmediaassessment.repository.TagRepository;
import com.cooksys.socialmediaassessment.repository.TweetRepository;
import com.cooksys.socialmediaassessment.repository.UserRepository;

//TODO: ADD EXCEPTIONS
@Service
public class TweetService {

	private TweetRepository tRepo;
	private UserRepository uRepo;
	private TagRepository tagRepo;

	public TweetService(TweetRepository tRepo, UserRepository uRepo, TagRepository tagRepo) {
		this.tRepo = tRepo;
		this.uRepo = uRepo;
		this.tagRepo = tagRepo;
	}

	public List<Tweet> getTweets() {
		List<Tweet> tweets = this.tRepo.findAll();
		tweets.removeIf(t -> t.getVisible() == false);
		return tweets;
	}

	public Tweet createTweet(String content, Credentials creds) {
		User author = this.uRepo.findUserByCredentials(creds);
		Tweet tweet = new Tweet();
		tweet.setContent(content);
		tweet.setAuthor(author);
		tweet.setPosted(new Timestamp(Instant.now().toEpochMilli()));
		tweet.setVisible(true);
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

	public List<String> parseHashtags(String content) {
		String patternString = "[#]{1}[A-Za-z0-9-_]{1,}";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(content);
		List<String> hashtags = new ArrayList<String>();
		while (matcher.find()) {
			hashtags.add(matcher.group());
		}
		return hashtags;
	}

	public List<String> parseMentions(String content) {
		String patternString = "[@]{1}[A-Za-z0-9-_]{1,}";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(content);
		List<String> mentions = new ArrayList<String>();
		while (matcher.find()) {
			mentions.add(matcher.group());
		}
		return mentions;
	}
}
