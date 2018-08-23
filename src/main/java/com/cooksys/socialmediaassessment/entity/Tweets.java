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

@Entity
public class Tweets {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Users author;

	private String content;

	@NotNull
	private Timestamp posted;

	@NotNull
	private Boolean deleted;

	@OneToMany
	private Set<Tweets> replies;

	@ManyToOne
	@JoinColumn(name = "inReplyTo_id")
	private Tweets inReplyTo;

	@OneToMany
	private Set<Tweets> repost;

	@ManyToOne
	@JoinColumn(name = "repostOf_id")
	private Tweets repostOf;

	@ManyToMany(mappedBy = "likedTweets")
	private Set<Users> likes;

	@ManyToMany(mappedBy = "mentions")
	private Set<Users> mentionedUsers;

	@ManyToMany(mappedBy = "tweets")
	private Set<Tags> hashTags;

	public Tweets(Integer id, @NotNull Users author, String content, @NotNull Timestamp posted,
			@NotNull Boolean deleted, Set<Tweets> replies, Tweets inReplyTo, Set<Tweets> repost, Tweets repostOf,
			Set<Users> likes, Set<Users> mentionedUsers, Set<Tags> hashTags) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.posted = posted;
		this.deleted = deleted;
		this.replies = replies;
		this.inReplyTo = inReplyTo;
		this.repost = repost;
		this.repostOf = repostOf;
		this.likes = likes;
		this.mentionedUsers = mentionedUsers;
		this.hashTags = hashTags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Set<Tweets> getReplies() {
		return replies;
	}

	public void setReplies(Set<Tweets> replies) {
		this.replies = replies;
	}

	public Tweets getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(Tweets inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public Set<Tweets> getRepost() {
		return repost;
	}

	public void setRepost(Set<Tweets> repost) {
		this.repost = repost;
	}

	public Tweets getRepostOf() {
		return repostOf;
	}

	public void setRepostOf(Tweets repostOf) {
		this.repostOf = repostOf;
	}

	public Set<Users> getLikes() {
		return likes;
	}

	public void setLikes(Set<Users> likes) {
		this.likes = likes;
	}

	public Set<Users> getMentionedUsers() {
		return mentionedUsers;
	}

	public void setMentionedUsers(Set<Users> mentionedUsers) {
		this.mentionedUsers = mentionedUsers;
	}

	public Set<Tags> getHashTags() {
		return hashTags;
	}

	public void setHashTags(Set<Tags> hashTags) {
		this.hashTags = hashTags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((hashTags == null) ? 0 : hashTags.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inReplyTo == null) ? 0 : inReplyTo.hashCode());
		result = prime * result + ((likes == null) ? 0 : likes.hashCode());
		result = prime * result + ((mentionedUsers == null) ? 0 : mentionedUsers.hashCode());
		result = prime * result + ((posted == null) ? 0 : posted.hashCode());
		result = prime * result + ((replies == null) ? 0 : replies.hashCode());
		result = prime * result + ((repost == null) ? 0 : repost.hashCode());
		result = prime * result + ((repostOf == null) ? 0 : repostOf.hashCode());
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
		Tweets other = (Tweets) obj;
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
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (hashTags == null) {
			if (other.hashTags != null)
				return false;
		} else if (!hashTags.equals(other.hashTags))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inReplyTo == null) {
			if (other.inReplyTo != null)
				return false;
		} else if (!inReplyTo.equals(other.inReplyTo))
			return false;
		if (likes == null) {
			if (other.likes != null)
				return false;
		} else if (!likes.equals(other.likes))
			return false;
		if (mentionedUsers == null) {
			if (other.mentionedUsers != null)
				return false;
		} else if (!mentionedUsers.equals(other.mentionedUsers))
			return false;
		if (posted == null) {
			if (other.posted != null)
				return false;
		} else if (!posted.equals(other.posted))
			return false;
		if (replies == null) {
			if (other.replies != null)
				return false;
		} else if (!replies.equals(other.replies))
			return false;
		if (repost == null) {
			if (other.repost != null)
				return false;
		} else if (!repost.equals(other.repost))
			return false;
		if (repostOf == null) {
			if (other.repostOf != null)
				return false;
		} else if (!repostOf.equals(other.repostOf))
			return false;
		return true;
	}
}
