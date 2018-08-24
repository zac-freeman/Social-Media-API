package com.cooksys.socialmediaassessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByCredentials(Credentials credentials);

	User findUserByCredentialsUsername(String username);

	User findUserByCredentialsUsernameAndCredentialsPassword(String username, String password);
}
