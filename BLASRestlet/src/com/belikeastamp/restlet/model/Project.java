package com.belikeastamp.restlet.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8823990281239852140L;
	
	@Id private Long id;
	@Index private Long userId;
	@Index private String name;
	private String subDate;
	private String theme;
	private String type;
	private String orderDate;
	private String perso;
	private String colors;
	@Index private Integer status;
	private int quantity;

	public Project() {}



	public Project(Long userId, String project_name, String sub_date, int project_status,
			String theme, String type, String order_date, int nbr_cards, String perso) {
		super();
		this.userId = userId;
		this.name = project_name;
		this.subDate = sub_date;
		this.status = project_status;
		this.theme = theme;
		this.type = type;
		this.orderDate = order_date;
		this.quantity = nbr_cards;
		this.perso = perso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSubDate() {
		return subDate;
	}



	public void setSubDate(String subDate) {
		this.subDate = subDate;
	}



	public String getTheme() {
		return theme;
	}



	public void setTheme(String theme) {
		this.theme = theme;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public String getPerso() {
		return perso;
	}



	public void setPerso(String perso) {
		this.perso = perso;
	}



	public String getColors() {
		return colors;
	}



	public void setColors(String colors) {
		this.colors = colors;
	}
	
	
}
