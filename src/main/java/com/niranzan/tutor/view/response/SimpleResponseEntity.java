package com.niranzan.tutor.view.response;

import java.time.LocalDateTime;

import com.niranzan.tutor.constant.AppConstants;

public class SimpleResponseEntity {
	private LocalDateTime time;
	private int statusCode;
	private String statusMessage;
	private Object data;

	public SimpleResponseEntity() {
		this.time = LocalDateTime.now();
		this.statusCode = 200;
		this.statusMessage = AppConstants.SUCCESS_RESPONSE_MSG;
	}

	public SimpleResponseEntity(int statusCode, String statusMessage, Object data) {
		this();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.data = data;
	}

	/**
	 * @return the time
	 */
	public LocalDateTime getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
}