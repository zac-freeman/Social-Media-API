package com.cooksys.socialmediaassessment.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Collections;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.embeddable.Profile;
import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.entity.User;
import com.cooksys.socialmediaassessment.repository.TweetRepository;
import com.cooksys.socialmediaassessment.repository.UserRepository;

//TODO: ADD EXCEPTIONS AFTER FINISHING ENDPOINTS
//TODO: HANDLE DEACTIVATED USERS
//TODO: HANDLE HIDDEN TWEETS
@Service
public class UserService {

	private UserRepository uRepo;
	private TweetRepository tRepo;

	public UserService(UserRepository uRepo, TweetRepository tRepo) {
		this.uRepo = uRepo;
		this.tRepo = tRepo;
	}


	public boolean exists(String username) {
		return (this.uRepo.findUserByCredentialsUsername(username) != null);
	}

	public List<User> getUsers() {
		List<User> users = this.uRepo.findAll();
		users.removeIf(u -> u.getActive() == false);
		return users;
	}

	public User createUser(User user) {
		if (!this.exists(user.getCredentials().getUsername())) {
			user.setJoined(new Timestamp(Instant.now().toEpochMilli()));
			user.setActive(true);
			return this.uRepo.save(user);
		}
		return null;
	}

	public User getUser(String username) {
		User user = this.uRepo.findUserByCredentialsUsername(username);
		if (user != null && user.getActive() == true) {
			return user;
		}
		return null;
	}

	public User updateProfile(String username, User user) {
		String givenUsername = user.getCredentials().getUsername();
		String givenPassword = user.getCredentials().getPassword();

		if (givenUsername.equals(username)) {
			User userToUpdate = this.uRepo.findUserByCredentialsUsernameAndCredentialsPassword(givenUsername,
					givenPassword);
			Profile oldProfile = userToUpdate.getProfile();
			Profile newProfile = user.getProfile();
			if (newProfile.getFirstName() != null) {
				oldProfile.setFirstName(newProfile.getFirstName());
			}
			if (user.getProfile().getLastName() != null) {
				oldProfile.setLastName(newProfile.getLastName());
			}
			if (user.getProfile().getFirstName() != null) {
				oldProfile.setPhone(newProfile.getPhone());
			}
			if (user.getProfile().getEmail() != null) {
				oldProfile.setEmail(newProfile.getEmail());
			}
			return this.uRepo.save(userToUpdate);
		}
		return null;
	}

	public User deactivateUser(String username, String password) {
		User deactivatedUser = this.uRepo.findUserByCredentialsUsernameAndCredentialsPassword(username, password);
		deactivatedUser.setActive(false);
		for (Tweet tweet : deactivatedUser.getTweets()) {
			tweet.setVisible(false);
			this.tRepo.save(tweet);
		}
		this.uRepo.save(deactivatedUser);
		return deactivatedUser;
	}

	public void followUser(String usernameToFollow, Credentials followerCreds) {
		User followee = this.uRepo.findUserByCredentialsUsername(usernameToFollow);
		User follower = this.uRepo.findUserByCredentialsUsernameAndCredentialsPassword(followerCreds.getUsername(),
				followerCreds.getPassword());

		followee.getFollowers().add(follower);
		follower.getFollowing().add(followee);
		this.uRepo.save(followee);
		this.uRepo.save(follower);
	}

	public void unfollowUser(String usernameToFollow, Credentials followerCreds) {
		User followee = this.uRepo.findUserByCredentialsUsername(usernameToFollow);
		User follower = this.uRepo.findUserByCredentialsUsernameAndCredentialsPassword(followerCreds.getUsername(),
				followerCreds.getPassword());

		followee.getFollowers().remove(follower);
		follower.getFollowing().remove(followee);
		this.uRepo.save(followee);
		this.uRepo.save(follower);
	}

	public List<Tweet> getFeed(String username) {
		User user = this.getUser(username);
		List<User> following = user.getFollowing();
		List<Tweet> feed = user.getTweets();
		for (User followee : following) {
			feed.addAll(followee.getTweets());
		}
		Collections.sort(feed, (t2, t1) -> t1.getPosted().compareTo(t2.getPosted()));
		return feed;
	}

	public List<Tweet> getTweets(String username) {
		List<Tweet> tweets = this.getUser(username).getTweets();
		Collections.sort(tweets, (t2, t1) -> t1.getPosted().compareTo(t2.getPosted()));
		return tweets;
	}

	public List<Tweet> getMentions(String username) {
		List<Tweet> mentions = this.getUser(username).getMentions();
		Collections.sort(mentions, (t2, t1) -> t1.getPosted().compareTo(t2.getPosted()));
		return mentions;
	}

	public List<User> getFollowers(String username) {
		User followee = this.uRepo.findUserByCredentialsUsername(username);
		return followee.getFollowers();
	}

	public List<User> getFollowing(String username) {
		User follower = this.uRepo.findUserByCredentialsUsername(username);
		return follower.getFollowing();
	}
}
