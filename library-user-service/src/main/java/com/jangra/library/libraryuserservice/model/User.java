package com.jangra.library.libraryuserservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6178054163022548905L;

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", isActive=" + isActive + ", age=" + age + ", emailId="
				+ emailId + ", isLibrarian=" + isLibrarian + "]";
	}
	@Id
	@GeneratedValue
	private Long userId;
	
	@NotNull(message = "Name can not be null")
	@NotBlank(message = "Name can not be blank")
	private String name;
	
	private Boolean isActive = true;
	private int age;
	@Column(unique = true)
	
	@Pattern(regexp=".+@.+\\.[a-z]+")
	@NotNull(message = "Email can not be null")
	private String emailId;
	private Boolean isLibrarian=false;
	
	public User() {
		
	}
	
	public User(Long userId, String name, int age, String emailId, Boolean isLibrarian,Boolean isActive) {
		super();
		this.isActive=isActive;
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.emailId = emailId;
		this.isLibrarian = isLibrarian;
	}
	
	
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmailId() {
		return emailId;
	}

	public Boolean getIsLibrarian() {
		return isLibrarian;
	}
	public void setIsLibrarian(Boolean isLibrarian) {
		this.isLibrarian = isLibrarian;
	}
	
	
}
