package com.belikeastamp.restlet.model;

import java.io.Serializable;

import javax.persistence.Id;

public class Workshop implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2186340398990436772L;
	/**
	 * 
	 */
	@Id
	private Long id;
	private String theme;
	private String address;
	private String hostname;
	private String town;
	private String date;
	private Integer capacity;
	private Integer registered;
	
	
	public Workshop() {}

	public Workshop(String theme, String address, String hostname, String town,
			String date, Integer capacity, Integer registered) {
		super();
		this.theme = theme;
		this.address = address;
		this.hostname = hostname;
		this.town = town;
		this.date = date;
		this.capacity = capacity;
		this.registered = registered;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getTheme() {
		return theme;
	}


	public void setTheme(String theme) {
		this.theme = theme;
	}


	public String getTown() {
		return town;
	}


	public void setTown(String town) {
		this.town = town;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public int getRegistered() {
		return registered;
	}


	public void setRegistered(int registered) {
		this.registered = registered;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
