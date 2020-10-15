package com.niranzan.tutor.view.response;

public class JwtResponse {
	private String token;
	private String type = "Bearer";

	public JwtResponse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param token
	 */
	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	/**
	 * @param token
	 * @param type
	 */
	public JwtResponse(String token, String type) {
		super();
		this.token = token;
		this.type = type;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}