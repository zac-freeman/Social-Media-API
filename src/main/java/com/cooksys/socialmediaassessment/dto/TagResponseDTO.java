package com.cooksys.socialmediaassessment.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class TagResponseDTO {

	@NotNull
	private String label;

	@NotNull
	private Timestamp firstUsed;

	@NotNull
	private Timestamp lastUsed;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstUsed == null) ? 0 : firstUsed.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((lastUsed == null) ? 0 : lastUsed.hashCode());
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
		TagResponseDTO other = (TagResponseDTO) obj;
		if (firstUsed == null) {
			if (other.firstUsed != null)
				return false;
		} else if (!firstUsed.equals(other.firstUsed))
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
		return true;
	}
}
