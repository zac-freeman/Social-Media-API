package com.cooksys.socialmediaassessment.dto;

public class TweetResponseDTO {

	private Integer id;
	private UserResponseDTO author;
	private long posted;
	private String content;
	private TweetResponseDTO inReplyTo;
	private TweetResponseDTO repostOf; // TODO: ask what the contents of these tweets should be

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserResponseDTO getAuthor() {
		return author;
	}

	public void setAuthor(UserResponseDTO author) {
		this.author = author;
	}

	public long getPosted() {
		return posted;
	}

	public void setPosted(long posted) {
		this.posted = posted;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TweetResponseDTO getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(TweetResponseDTO inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public TweetResponseDTO getRepostOf() {
		return repostOf;
	}

	public void setRepostOf(TweetResponseDTO repostOf) {
		this.repostOf = repostOf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (posted ^ (posted >>> 32));
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
		TweetResponseDTO other = (TweetResponseDTO) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (posted != other.posted)
			return false;
		return true;
	}
}
