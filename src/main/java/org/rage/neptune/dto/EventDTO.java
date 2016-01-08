package org.rage.neptune.dto;

import java.util.Date;

public class EventDTO {

	private String id;
	private String description;
	private String title;
	private Date creation;

	public EventDTO() {
	}

	public EventDTO(String id, String title, String description, Date creation) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.creation = creation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

}
