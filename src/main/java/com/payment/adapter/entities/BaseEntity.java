package com.payment.adapter.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseEntity {

	@Id
	@Indexed
	protected String id;

	@Indexed
	@JsonIgnore
	protected boolean active = true;

	@JsonIgnore
	@CreatedDate
	protected Date createdDate;

	@JsonIgnore
	@LastModifiedDate
	protected Date updateDate;

	@JsonIgnore
	@Version
	private Long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}

	@JsonIgnore
	public abstract boolean isValid();
}
