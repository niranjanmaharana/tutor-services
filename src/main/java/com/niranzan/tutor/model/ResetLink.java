/**
 * 
 */
package com.niranzan.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Niranjan
 *
 */
@Entity
@Table(name = "RESET_LINK")
public class ResetLink extends BaseEntity {
	@Column(name = "USER_ID")
	private long userId;
	@Column(name = "LINK")
	private String link;
	
	public ResetLink() {
		super();
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
}