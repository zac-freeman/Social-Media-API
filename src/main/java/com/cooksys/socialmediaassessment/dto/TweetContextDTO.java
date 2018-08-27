package com.cooksys.socialmediaassessment.dto;

import java.util.List;

import com.cooksys.socialmediaassessment.entity.Tweet;

public class TweetContextDTO {

	Tweet target;
	List<Tweet> before;
	List<Tweet> after;

	public Tweet getTarget() {
		return target;
	}
	public void setTarget(Tweet target) {
		this.target = target;
	}
	public List<Tweet> getBefore() {
		return before;
	}
	public void setBefore(List<Tweet> before) {
		this.before = before;
	}
	public List<Tweet> getAfter() {
		return after;
	}
	public void setAfter(List<Tweet> after) {
		this.after = after;
	}
}
