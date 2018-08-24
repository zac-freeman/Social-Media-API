package com.cooksys.socialmediaassessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaassessment.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {

}