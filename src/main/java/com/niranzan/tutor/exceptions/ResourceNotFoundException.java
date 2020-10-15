/**
 * 
 */
package com.niranzan.tutor.exceptions;

/**
 * @author Niranjan
 *
 */
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -2912994441810155305L;
	
	/**
	 * 
	 */
	public ResourceNotFoundException() {
		super("Resource not found exception !");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}