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
	@Index private String type;
	private Blob file;
	
	public FileStorage() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCorrespondingId() {
		return correspondingId;
	}

	public void setCorrespondingId(Long correspondingId) {
		this.correspondingId = correspondingId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}

	public FileStorage(Long correspondingId, String type, Blob file) {
		super();
		this.correspondingId = correspondingId;
		this.type = type;
		this.file = file;
	}

	@Override
	public String toString() {
		return "FileStorage [id=" + id + ", correspondingId=" + correspondingId
				+ ", type=" + type + ", file=" + file.hashCode() + "]";
	}
	
	
	

	

	
	
	
	
}
