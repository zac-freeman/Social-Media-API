package com.cooksys.socialmediaassessment.dto;

import javax.validation.constraints.NotNull;

import com.cooksys.socialmediaassessment.embeddable.Credentials;

//TODO: dont use this
public class TweetRequestDTO {

	@NotNull
	String Content;

	@NotNull
	Credentials credentials;

	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Content == null) ? 0 : Content.hashCode());
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
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
		TweetRequestDTO other = (TweetRequestDTO) obj;
		if (Content == null) {
			if (other.Content != null)
				return false;
		} else if (!Content.equals(other.Content))
			return false;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		return true;
	}
}
