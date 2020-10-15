/**
 * 
 */
package com.niranzan.tutor.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Niranjan
 *
 */

@Entity
@Table(name = "USER_PROFILE", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "USERNAME" }, name = "UNIQUE_USERNAME"),
		@UniqueConstraint(columnNames = { "EMAIL" }, name = "UNIQUE_EMAIL"),
		@UniqueConstraint(columnNames = { "MOBILE" }, name = "UNIQUE_MOBILE") })
public class UserProfile extends BaseEntity {
	@Column(name = "FIRST_NM")
	private String firstNm;

	@Column(name = "LAST_NM")
	private String lastNm;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "MOBILE", length = 10)
	private String mobile;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PRFL_PIC")
	private String prflPic;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_AUTHORITY", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private List<Authority> authorities = new ArrayList<>();

	public UserProfile() {
		super();
	}

	/**
	 * @param firstNm
	 * @param lastNm
	 * @param username
	 * @param email
	 * @param mobile
	 * @param password
	 * @param prflPic
	 * @param authorities
	 */
	public UserProfile(String firstNm, String lastNm, String username, String email, String mobile, String password,
			String prflPic) {
		super();
		this.firstNm = firstNm;
		this.lastNm = lastNm;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.prflPic = prflPic;
	}

	/**
	 * @return the firstNm
	 */
	public String getFirstNm() {
		return firstNm;
	}

	/**
	 * @param firstNm the firstNm to set
	 */
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}

	/**
	 * @return the lastNm
	 */
	public String getLastNm() {
		return lastNm;
	}

	/**
	 * @param lastNm the lastNm to set
	 */
	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the prflPic
	 */
	public String getPrflPic() {
		return prflPic;
	}

	/**
	 * @param prflPic the prflPic to set
	 */
	public void setPrflPic(String prflPic) {
		this.prflPic = prflPic;
	}

	/**
	 * @return the authorities
	 */
	public List<Authority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
}