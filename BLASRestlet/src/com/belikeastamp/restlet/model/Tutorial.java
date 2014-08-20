package com.belikeastamp.restlet.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Tutorial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1778891610597448685L;
	
	@Id private Long id;
	@Index String title;
	String file;
	Boolean available;
	String date;
	Integer onDemand;
	
	public Tutorial(){}
	
	public Tutorial(String title, Boolean avail, String file, String date, Integer demand) {
		super();
		this.title = title;
		this.available = avail;
		this.file = file;
		this.date = date;
		this.onDemand = demand;
	}
	
	public String getTitle() {
		return title;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getOnDemand() {
		return onDemand;
	}

	public void setOnDemand(Integer onDemand) {
		this.onDemand = onDemand;
	}
	
	@Override
	public String toString() {
		return "Tutorial [title=" + title + ", file=" + file + ", available="
				+ available + ", date=" + date + ", demand=" + onDemand + "]";
	}

	
	
	
	
	

}
