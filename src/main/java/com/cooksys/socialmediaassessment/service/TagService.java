package com.cooksys.socialmediaassessment.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassessment.entity.Tag;
import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.repository.TagRepository;

@Service
public class TagService {

	private TagRepository tagRepo;

	public TagService(TagRepository tagRepo) {
		this.tagRepo = tagRepo;
	}


	public boolean exists(String label) {
		return (this.tagRepo.findTagByLabel(label) != null);
	}

	public List<Tag> getTags() {
		return tagRepo.findAll();
	}

	public List<Tweet> getTweets(String label) {
		List<Tweet> tweets = (List<Tweet>) this.tagRepo.findTagByLabel(label).getTweets();
		Collections.sort(tweets, (t2, t1) -> t1.getPosted().compareTo(t2.getPosted()));
		return tweets;
	}
}
