package com.inam.tablemaster.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "names")
public class TableMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 200, message = "Guest must be no less than 2!")
	private String name;

	@NotNull
    @Min(value= 0,  message ="Number of guests must be between 1 and 10!")
    private Integer guests;

	//@NotNull
	//@Size(min = 2, max = 200, message = "Description must be no less than 2!")
	private String notes;
	
	// One to Many relationship with user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")

	private User user;
	
	
	
	// Empty Constructor
	public TableMaster() {
		super();
	}
	
	// Constructor with Id, Created_at and updated_at checked off
	public TableMaster(@NotNull @Size(min = 2, max = 200, message = "Guest must be no less than 2!") String name,
			@NotNull @Min(value = 0, message = "Number of guests must be between 1 and 10!") Integer guests,
			String notes, User user) {
		super();
		this.name = name;
		this.guests = guests;
		this.notes = notes;
		this.user = user;
	}
	
	// Constructor with all the field checked in
	public TableMaster(Long id,
			@NotNull @Size(min = 2, max = 200, message = "Guest must be no less than 2!") String name,
			@NotNull @Min(value = 0, message = "Number of guests must be between 1 and 10!") Integer guests,
			String notes, User user, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.guests = guests;
		this.notes = notes;
		this.user = user;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGuests() {
		return guests;
	}

	public void setGuests(Integer guests) {
		this.guests = guests;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	// This will not allow the createdAt column to be updated after creation
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	
}
