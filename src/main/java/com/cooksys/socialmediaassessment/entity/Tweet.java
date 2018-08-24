package com.cooksys.socialmediaassessment.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity(name = "tweets")
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	private String content;

	@NotNull
	private Timestamp posted;

	@NotNull
	private Boolean visible;

	@OneToMany
	private Set<Tweet> replies;

	@ManyToOne
	@JoinColumn(name = "inReplyTo_id")
	private Tweet inReplyTo;

	@OneToMany
	private Set<Tweet> reposts;

	@ManyToOne
	@JoinColumn(name = "repostOf_id")
	private Tweet repostOf;

	@ManyToMany(mappedBy = "likedTweets")
	private Set<User> likes;

	@ManyToMany(mappedBy = "mentions")
	private Set<User> mentionedUsers;

	@ManyToMany(mappedBy = "tweets")
	private Set<Tag> hashtags;

	public Tweet() {}

	public Tweet(Integer id, @NotNull User author, String content, @NotNull Timestamp posted,
			@NotNull Boolean visible, Set<Tweet> replies, Tweet inReplyTo, Set<Tweet> reposts, Tweet repostOf,
			Set<User> likes, Set<User> mentionedUsers, Set<Tag> hashtags) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.posted = posted;
		this.visible = visible;
		this.replies = replies;
		this.inReplyTo = inReplyTo;
		this.reposts = reposts;
		this.repostOf = repostOf;
		this.likes = likes;
		this.mentionedUsers = mentionedUsers;
		this.hashtags = hashtags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getPosted() {
		return posted;
	}

	public void setPosted(Timestamp posted) {
		this.posted = posted;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Set<Tweet> getReplies() {
		return replies;
	}

	public void setReplies(Set<Tweet> replies) {
		this.replies = replies;
	}

	public Tweet getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(Tweet inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public Set<Tweet> getReposts() {
		return reposts;
	}

	public void setReposts(Set<Tweet> reposts) {
		this.reposts = reposts;
	}

	public Tweet getRepostOf() {
		return repostOf;
	}

	public void setRepostOf(Tweet repostOf) {
		this.repostOf = repostOf;
	}

	public Set<User> getLikes() {
		return likes;
	}

	public void setLikes(Set<User> likes) {
		this.likes = likes;
	}

	public Set<User> getMentionedUsers() {
		return mentionedUsers;
	}

	public void setMentionedUsers(Set<User> mentionedUsers) {
		this.mentionedUsers = mentionedUsers;
	}

	public Set<Tag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(Set<Tag> hashtags) {
		this.hashtags = hashtags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((visible == null) ? 0 : visible.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((posted == null) ? 0 : posted.hashCode());
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
		Tweet other = (Tweet) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (visible == null) {
			if (other.visible != null)
				return false;
		} else if (!visible.equals(other.visible))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (posted == null) {
			if (other.posted != null)
				return false;
		} else if (!posted.equals(other.posted))
			return false;
		return true;
	}
}
