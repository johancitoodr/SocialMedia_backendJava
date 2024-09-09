package com.sm.site.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sm.site.post.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name= "tbl_user")
public class User {
	
	
	@Id
	@GeneratedValue
	Integer id;
	@NotNull(message= "Complete All Fields")
	String name;
	@NotNull(message= "Complete All Fields")
	String lastName;
	@NotEmpty(message= "Complete All Fields")
	String birthDate;
	@NotNull(message= "Complete All Fields")
	String email;
	
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List <Post> post;
	
	public List <Post> getPost(){
		return post;
	}
	 
	public User() {}
	
	public User(Integer id, String name,String lastName,String birthDate,String email) {
		
		this.id=id; 
		this.birthDate=birthDate;
		this.name=name;
		this.lastName=lastName;
		this.email=email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		
		return id;
	}
	
}
