package com.belikeastamp.restlet.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class FileStorage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6048048167525993267L;
	
	@Id private Long id;
	@Index private Long correspondingId;
	private Blob file;
	
	public FileStorage() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTutorialId() {
		return correspondingId;
	}

	public void setTutorialId(Long tutorialId) {
		this.correspondingId = tutorialId;
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}

	public FileStorage(Long tutorialId, Blob file) {
		super();
		this.correspondingId = tutorialId;
		this.file = file;
	}

	@Override
	public String toString() {
		return "TutorialFile [id=" + id + ", tutorialId=" + correspondingId
				+ ", file=" + file.hashCode() + "]";
	}

	
	
	
	
}
