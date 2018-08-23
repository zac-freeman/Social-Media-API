package com.cooksys.socialmediaassessment.entity;

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

@Entity
public class Users {

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
	String joined;

	@NotNull
	Boolean active;

	@ManyToMany(mappedBy = "followers")
	private Set<Users> following;

	@ManyToMany
	@JoinTable(name = "followee_follower")
	private Set<Users> followers;

	@OneToMany
	private Set<Tweets> tweets;

	@ManyToMany
	@JoinTable(name = "user_mention")
	private Set<Tweets> mentions;

	@ManyToMany
	@JoinTable(name = "user_like")
	private Set<Tweets> likedTweets;

	public Users() {}

	public Users(Integer id, @NotNull Credentials credentials, @NotNull Profile profile, @NotNull String joined,
			@NotNull Boolean active, Set<Users> following, Set<Users> followers, Set<Tweets> tweets,
			Set<Tweets> mentions, Set<Tweets> likedTweets) {
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

	public String getJoined() {
		return joined;
	}

	public void setJoined(String joined) {
		this.joined = joined;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Users> getFollowing() {
		return following;
	}

	public void setFollowing(Set<Users> following) {
		this.following = following;
	}

	public Set<Users> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Users> followers) {
		this.followers = followers;
	}

	public Set<Tweets> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweets> tweets) {
		this.tweets = tweets;
	}

	public Set<Tweets> getMentions() {
		return mentions;
	}

	public void setMentions(Set<Tweets> mentions) {
		this.mentions = mentions;
	}

	public Set<Tweets> getLikedTweets() {
		return likedTweets;
	}

	public void setLikedTweets(Set<Tweets> likedTweets) {
		this.likedTweets = likedTweets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
		result = prime * result + ((followers == null) ? 0 : followers.hashCode());
		result = prime * result + ((following == null) ? 0 : following.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((joined == null) ? 0 : joined.hashCode());
		result = prime * result + ((likedTweets == null) ? 0 : likedTweets.hashCode());
		result = prime * result + ((mentions == null) ? 0 : mentions.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		Users other = (Users) obj;
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
		if (followers == null) {
			if (other.followers != null)
				return false;
		} else if (!followers.equals(other.followers))
			return false;
		if (following == null) {
			if (other.following != null)
				return false;
		} else if (!following.equals(other.following))
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
		if (likedTweets == null) {
			if (other.likedTweets != null)
				return false;
		} else if (!likedTweets.equals(other.likedTweets))
			return false;
		if (mentions == null) {
			if (other.mentions != null)
				return false;
		} else if (!mentions.equals(other.mentions))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (tweets == null) {
			if (other.tweets != null)
				return false;
		} else if (!tweets.equals(other.tweets))
			return false;
		return true;
	}
}
