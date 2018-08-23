package com.cooksys.socialmediaassessment.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Tags {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotNull
	private String label;

	@NotNull
	private Timestamp firstUsed;

	@NotNull
	private Timestamp lastUsed;

	@ManyToMany
	@JoinTable(name = "hashtag_tweet")
	private Set<Tweets> tweets;

	public Tags() {}

	public Tags(Integer id, @NotNull String label, @NotNull Timestamp firstUsed, @NotNull Timestamp lastUsed,
			Set<Tweets> tweets) {
		super();
		this.id = id;
		this.label = label;
		this.firstUsed = firstUsed;
		this.lastUsed = lastUsed;
		this.tweets = tweets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Timestamp getFirstUsed() {
		return firstUsed;
	}

	public void setFirstUsed(Timestamp firstUsed) {
		this.firstUsed = firstUsed;
	}

	public Timestamp getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Timestamp lastUsed) {
		this.lastUsed = lastUsed;
	}

	public Set<Tweets> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweets> tweets) {
		this.tweets = tweets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstUsed == null) ? 0 : firstUsed.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((lastUsed == null) ? 0 : lastUsed.hashCode());
		result = prime * result + ((tweets == null) ? 0 : tweets.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tags other = (Tags) obj;
		if (firstUsed == null) {
			if (other.firstUsed != null)
				return false;
		} else if (!firstUsed.equals(other.firstUsed))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (lastUsed == null) {
			if (other.lastUsed != null)
				return false;
		} else if (!lastUsed.equals(other.lastUsed))
			return false;
		if (tweets == null) {
			if (other.tweets != null)
				return false;
		} else if (!tweets.equals(other.tweets))
			return false;
		return true;
	}
}
