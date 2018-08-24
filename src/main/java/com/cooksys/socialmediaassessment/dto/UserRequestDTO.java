package com.cooksys.socialmediaassessment.dto;

import com.cooksys.socialmediaassessment.embeddable.Credentials;
import com.cooksys.socialmediaassessment.embeddable.Profile;

//TODO: remove and replace with what is needed for specific requests
public class UserRequestDTO {

	private Credentials credentials;
	private Profile profile;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
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
		UserRequestDTO other = (UserRequestDTO) obj;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}
}
