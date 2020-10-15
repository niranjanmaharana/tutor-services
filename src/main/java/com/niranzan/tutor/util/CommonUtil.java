/**
 * 
 */
package com.niranzan.tutor.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Niranjan
 *
 */
public class CommonUtil {
	public static String generateRandomToken() {
	    return RandomStringUtils.random(60, true, true);
	}
}