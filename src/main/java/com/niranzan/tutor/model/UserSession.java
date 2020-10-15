/**
 * 
 */
package com.niranzan.tutor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author Niranjan
 *
 */

@Entity
@Table(name = "USER_SESSION")
public class UserSession extends BaseEntity {
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "USER_AGENT")
	private String userAgent;
	@Column(name = "ORIGIN")
	private String origin;
	@Column(name = "COUNTRY")
	private String country;
	@Column(name = "LOGIN_DTM")
	@CreationTimestamp
	private Date loginDtm;
	@Column(name = "LOGOUT_DTM")
	private Date logoutDtm;
	@Column(name = "SUCCESS")
	private boolean success;
	@Column(name = "CAUSE")
	private String cause;
	
	public UserSession() {
		super();
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the loginDtm
	 */
	public Date getLoginDtm() {
		return loginDtm;
	}

	/**
	 * @param loginDtm the loginDtm to set
	 */
	public void setLoginDtm(Date loginDtm) {
		this.loginDtm = loginDtm;
	}

	/**
	 * @return the logoutDtm
	 */
	public Date getLogoutDtm() {
		return logoutDtm;
	}

	/**
	 * @param logoutDtm the logoutDtm to set
	 */
	public void setLogoutDtm(Date logoutDtm) {
		this.logoutDtm = logoutDtm;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}
}