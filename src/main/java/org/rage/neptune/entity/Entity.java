package org.rage.neptune.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name="entity")
public class Entity {

	@Id
	private String id;
	private String description;
	private String creator;
	private Date creation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

}
