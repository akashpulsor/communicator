package com.communicate.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="MEDIA_LIBRARY")
public class MediaLibrary  {
	
	
	
	@EmbeddedId
	private MediaKey mediaKey;
	
	
	@Enumerated(EnumType.STRING)
	private MediaType type;

	@Column
	private String originalMediaName;

	/**
	 * @return the type
	 */
	public MediaType getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(MediaType type) {
		this.type = type;
	}
	




	/**
	 * @return the mediaKey
	 */
	public MediaKey getMediaKey() {
		return mediaKey;
	}


	/**
	 * @param mediaKey the mediaKey to set
	 */
	public void setMediaKey(MediaKey mediaKey) {
		this.mediaKey = mediaKey;
	}


	
	/**
	 * @return the originalMediaName
	 */
	public String getOriginalMediaName() {
		return originalMediaName;
	}


	/**
	 * @param originalMediaName the originalMediaName to set
	 */
	public void setOriginalMediaName(String originalMediaName) {
		this.originalMediaName = originalMediaName;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mediaKey == null) ? 0 : mediaKey.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediaLibrary other = (MediaLibrary) obj;
		if (mediaKey == null) {
			if (other.mediaKey != null)
				return false;
		} else if (!mediaKey.equals(other.mediaKey))
			return false;
		if (type != other.type)
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MediaLibrary [mediaKey=" + mediaKey + ", type=" + type + ", originalMediaName=" + originalMediaName
				+ "]";
	}

	
}
