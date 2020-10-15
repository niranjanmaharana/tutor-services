/**
 * 
 */
package com.niranzan.tutor.view.request;

/**
 * @author Niranjan
 *
 */
public class ResetPasswordRequestView {
	private String password;
	private String token;
	
	public ResetPasswordRequestView() {
		super();
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
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
}