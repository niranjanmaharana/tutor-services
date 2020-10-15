/**
 * 
 */
package com.niranzan.tutor.service;

/**
 * @author Niranjan
 *
 */
public interface PasswordValidator {
	/**
	 * @param password
	 * @return
	 */
	boolean isValid(String password);
}