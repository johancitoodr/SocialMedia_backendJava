package com.sm.site.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sm.site.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name= "post")
public class Post{
	
	
	@Id
	@GeneratedValue
	private Integer id;
	private String information;
	private String company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user; 
	public Post() {}
	public Post(Integer id, String information, String company, User user) {
		super();
		this.id = id;
		this.information = information;
		this.company = company;
		this.user=user;
		
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
