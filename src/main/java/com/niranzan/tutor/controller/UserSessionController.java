/**
 * 
 */
package com.niranzan.tutor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niranzan.tutor.constant.AppConstants;
import com.niranzan.tutor.service.UserSessionService;
import com.niranzan.tutor.view.response.SimpleResponseEntity;
import com.niranzan.tutor.view.response.UserSessionResponseView;

import io.swagger.annotations.Api;

/**
 * @author Niranjan
 *
 */

@Api(value = "Usersession", description = "Operations on user sessions.")
@RestController
@RequestMapping("/session")
public class UserSessionController {
	@Autowired
	private UserSessionService userSessionService;
	
	@GetMapping("/{username}")
	public ResponseEntity<SimpleResponseEntity> getUserSessionsByUsername(@PathVariable("username") String username) {
		List<UserSessionResponseView> responses = userSessionService.getSessionByUsername(username);
		return new ResponseEntity<SimpleResponseEntity>(
				new SimpleResponseEntity(HttpStatus.OK.value(), AppConstants.SUCCESS_RESPONSE_MSG, responses),
				HttpStatus.OK);
	}
}