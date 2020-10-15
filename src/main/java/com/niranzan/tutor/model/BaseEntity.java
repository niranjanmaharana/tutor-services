/**
 * 
 */
package com.niranzan.tutor.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Niranjan
 *
 */

@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CREATED_DTM")
	@CreationTimestamp
	private Date createdDtm;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_DTM")
	@UpdateTimestamp
	private Date updatedDtm;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Version
	@Column(name = "VERSION")
	private Integer version = 1;

	@Column(name = "ACTIVE", columnDefinition = "BIT DEFAULT TRUE")
	private boolean active = true;

	@Column(name = "VALID_FROM")
	@CreationTimestamp
	private Date validFrom;

	@Column(name = "VALID_TO")
	private Date validTo;

	public BaseEntity() {
		super();
	}

	@PrePersist
	public void prePersist() {
		if (validFrom == null) {
			validFrom = new Date();
		}
		if (validTo == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(8888, 11, 31, 0, 0, 0);
			validTo = calendar.getTime();
		}
		if (createdBy == null || createdBy.isEmpty()) {
			if(SecurityContextHolder.getContext().getAuthentication() != null) {
				createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
			}
		}
	}

	@PreUpdate
	public void preUpdate() {
		if (validFrom == null) {
			validFrom = new Date();
		}
		if (validTo == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(8888, 11, 31, 0, 0, 0);
			validTo = calendar.getTime();
		}
		if (updatedBy == null || updatedBy.isEmpty()) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			updatedBy = username;
		}
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the createdDtm
	 */
	public Date getCreatedDtm() {
		return createdDtm;
	}

	/**
	 * @param createdDtm
	 *            the createdDtm to set
	 */
	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	/**
	 * @return the updatedDtm
	 */
	public Date getUpdatedDtm() {
		return updatedDtm;
	}

	/**
	 * @param updatedDtm
	 *            the updatedDtm to set
	 */
	public void setUpdatedDtm(Date updatedDtm) {
		this.updatedDtm = updatedDtm;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the validFrom
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/**
	 * @param validFrom
	 *            the validFrom to set
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return the validTo
	 */
	public Date getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo
	 *            the validTo to set
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
}