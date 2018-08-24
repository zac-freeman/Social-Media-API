package com.cooksys.socialmediaassessment.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.embeddable.Profile;
import com.cooksys.socialmediaassessment.entity.User;
import com.cooksys.socialmediaassessment.repository.UserRepository;

	//TODO: ADD EXCEPTIONS AFTER FINISHING ENDPOINTS
	//TODO: HANDLE DEACTIVATED USERS
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

	//getFeed()
	//getTweets()
	//getMentions()

	public Collection<User> getFollowers(String username) {
		User followee = this.uRepo.findUserByCredentialsUsername(username);
		return followee.getFollowers();
	}

	public Collection<User> getFollowing(String username) {
		User follower = this.uRepo.findUserByCredentialsUsername(username);
		return follower.getFollowing();
	}
}