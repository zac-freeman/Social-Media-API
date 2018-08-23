package com.cooksys.socialmediaassessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaassessment.entity.Tweets;

@Repository
public interface TweetsRepository extends JpaRepository<Tweets, Integer> {

}