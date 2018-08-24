package com.cooksys.socialmediaassessment.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.embeddable.Profile;

@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotNull
	@Embedded
	private Credentials credentials;

	@NotNull
	@Embedded
	private Profile profile;

	@NotNull
	private Timestamp joined;

	@NotNull
	private Boolean active;

	@ManyToMany(mappedBy = "followers")
	private Set<User> following;

	@ManyToMany
	@JoinTable(name = "followee_follower")
	private Set<User> followers;

	@OneToMany
	private Set<Tweet> tweets;

	@ManyToMany
	@JoinTable(name = "user_mention")
	private Set<Tweet> mentions;

	@ManyToMany
	@JoinTable(name = "user_like")
	private Set<Tweet> likedTweets;

	public User() {}

	public User(Integer id, @NotNull Credentials credentials, @NotNull Profile profile, @NotNull Timestamp joined,
			@NotNull Boolean active, Set<User> following, Set<User> followers, Set<Tweet> tweets,
			Set<Tweet> mentions, Set<Tweet> likedTweets) {
		super();
		this.id = id;
		this.credentials = credentials;
		this.profile = profile;
		this.joined = joined;
		this.active = active;
		this.following = following;
		this.followers = followers;
		this.tweets = tweets;
		this.mentions = mentions;
		this.likedTweets = likedTweets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Timestamp getJoined() {
		return joined;
	}

	public void setJoined(Timestamp joined) {
		this.joined = joined;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public Set<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

	public Set<Tweet> getMentions() {
		return mentions;
	}

	public void setMentions(Set<Tweet> mentions) {
		this.mentions = mentions;
	}

	public Set<Tweet> getLikedTweets() {
		return likedTweets;
	}

	public void setLikedTweets(Set<Tweet> likedTweets) {
		this.likedTweets = likedTweets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((joined == null) ? 0 : joined.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		User other = (User) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (joined == null) {
			if (other.joined != null)
				return false;
		} else if (!joined.equals(other.joined))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}
}
