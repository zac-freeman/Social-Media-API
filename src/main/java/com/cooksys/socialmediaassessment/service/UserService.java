package com.cooksys.socialmediaassessment.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.embeddable.Profile;
import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.entity.User;
import com.cooksys.socialmediaassessment.repository.UserRepository;

	//TODO: ADD EXCEPTIONS AFTER FINISHING ENDPOINTS
	//TODO: HANDLE DEACTIVATED USERS
	//TODO: HANDLE HIDDEN TWEETS
	//TODO: CATCH DUPLICATE ENTRIES
@Service
public class UserService {

	private UserRepository uRepo;

	public UserService(UserRepository uRepo) {
		this.uRepo = uRepo;
	}

	public List<User> getUsers() {
		return this.uRepo.findAll();
	}

	//TODO: check if deactivated
	public User createUser(User user) {
		user.setJoined(new Timestamp(Instant.now().toEpochMilli()));
		user.setActive(true);
		return this.uRepo.save(user);
	}

	public User getUser(String username) {
		return this.uRepo.findUserByCredentialsUsername(username);
	}

	public User updateProfile(String username, User user) {
		String givenUsername = user.getCredentials().getUsername();
		String givenPassword = user.getCredentials().getPassword();

		if (givenUsername.equals(username)) {
			User userToUpdate = this.uRepo.findUserByCredentialsUsernameAndCredentialsPassword(givenUsername, givenPassword);
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
			oldProfile.setEmail(newProfile.getEmail());
			return this.uRepo.save(userToUpdate);
		}
		return null;
	}

	public User deactivateUser(String username, String password) {
		User deactivatedUser = this.uRepo.findUserByCredentialsUsernameAndCredentialsPassword(username, password);
		deactivatedUser.setActive(false);
		this.uRepo.save(deactivatedUser);
		return deactivatedUser;
	}

	public void followUser(String usernameToFollow, Credentials followerCreds) {
		User followee = this.uRepo.findUserByCredentialsUsername(usernameToFollow);
		User follower = this.uRepo.findUserByCredentialsUsernameAndCredentialsPassword(followerCreds.getUsername(), followerCreds.getPassword());

		followee.getFollowers().add(follower);
		follower.getFollowing().add(followee);
		this.uRepo.save(followee);
		this.uRepo.save(follower);
	}

	public void unfollowUser(String usernameToFollow, Credentials followerCreds) {
		User followee = this.uRepo.findUserByCredentialsUsername(usernameToFollow);
		User follower = this.uRepo.findUserByCredentialsUsernameAndCredentialsPassword(followerCreds.getUsername(), followerCreds.getPassword());

		followee.getFollowers().remove(follower);
		follower.getFollowing().remove(followee);
		this.uRepo.save(followee);
		this.uRepo.save(follower);
	}

	public Collection<Tweet> getFeed(String username) {
		User user = this.getUser(username);
		List<User> following = (List<User>) user.getFollowing();
		List<Tweet> feed = (List<Tweet>) user.getTweets();
		for (User followee : following) {
			feed.addAll(followee.getTweets());
		}
		Collections.sort(feed, (t1, t2) -> t1.getPosted().compareTo(t2.getPosted()));
		return feed;
	}

	public Collection<Tweet> getTweets(String username) {
		List<Tweet> tweets = (List<Tweet>) this.getUser(username).getTweets();
		Collections.sort(tweets, (t1, t2) -> t1.getPosted().compareTo(t2.getPosted()));
		return tweets;
	}

	public Collection<Tweet> getMentions(String username) {
		List<Tweet> mentions = (List<Tweet>) this.getUser(username).getMentions();
		Collections.sort(mentions, (t1, t2) -> t1.getPosted().compareTo(t2.getPosted()));
		return mentions;
	}

	public Collection<User> getFollowers(String username) {
		User followee = this.uRepo.findUserByCredentialsUsername(username);
		return followee.getFollowers();
	}

	public Collection<User> getFollowing(String username) {
		User follower = this.uRepo.findUserByCredentialsUsername(username);
		return follower.getFollowing();
	}
}
