package com.cooksys.socialmediaassessment.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassessment.embeddable.Profile;
import com.cooksys.socialmediaassessment.entity.Users;
import com.cooksys.socialmediaassessment.repository.UsersRepository;

@Service
public class UsersService {

	private UsersRepository uRepo;

	public UsersService(UsersRepository uRepo) {
		this.uRepo = uRepo;
	}

	public List<Users> getUsers() {
		return this.uRepo.findAll();
	}

	public Users createUser(Users user) {
		user.setJoined(new Timestamp(Instant.now().toEpochMilli()));
		user.setActive(true);
		return this.uRepo.save(user);
	}

	public Users getUserByName(String username) {
		return this.uRepo.findUsersByCredentialsUsername(username);
	}

	public Users updateProfile(String username, Users user) {
		String givenUsername = user.getCredentials().getUsername();
		String givenPassword = user.getCredentials().getPassword();

		if (givenUsername.equals(username)) {
			Users userToUpdate = this.uRepo.findUsersByCredentialsUsernameAndCredentialsPassword(givenUsername, givenPassword);
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
		return null;	//TODO: throw exceptions instead of returning null
	}

}
