/**
 * 
 */
package com.niranzan.tutor.constant;

import org.springframework.http.HttpStatus;

/**
 * @author Niranjan
 *
 */
public interface AppConstants {
	public static final String SUCCESS_RESPONSE_MSG = "Successful.";
	public static final String FAILURE_RESPONSE_MSG = "Failed!";
	
	public static final HttpStatus SUCCESS_RSPNS_STTS = HttpStatus.OK;
	public static final HttpStatus FAILURE_RSPNS_STTS = HttpStatus.INTERNAL_SERVER_ERROR;
	
	public static final String FAILURE_RESPONSE_DATA = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
	
	public static final String TOKEN_TYPE = "Bearer";
	
	public static final String UNKNOWN_VAL = "UNK";
	public static final String NA_VAL = "NA";
	
	public static final String CNSTRNT_UNQ_USERNAME = "UNIQUE_USERNAME";
	public static final String CNSTRNT_UNQ_USERNAME_MSG = "Username already registered!";
	public static final String CNSTRNT_UNQ_EMAIL = "UNIQUE_EMAIL";
	public static final String CNSTRNT_UNQ_EMAIL_MSG = "Email already registered!";
	public static final String CNSTRNT_UNQ_MOBILE = "UNIQUE_MOBILE";
	public static final String CNSTRNT_UNQ_MOBILE_MSG = "Mobile number already registered!";
}