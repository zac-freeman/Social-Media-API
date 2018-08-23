package com.cooksys.socialmediaassessment.dto;

import com.cooksys.socialmediaassessment.embeddable.Profile;

public class UsersResponseDTO {

	private String username;
	private Profile profile;
	private long joined;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public long getJoined() {
		return joined;
	}

	public void setJoined(long joined) {
		this.joined = joined;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (joined ^ (joined >>> 32));
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		UsersResponseDTO other = (UsersResponseDTO) obj;
		if (joined != other.joined)
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
