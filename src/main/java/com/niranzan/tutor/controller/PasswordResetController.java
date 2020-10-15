/**
 * 
 */
package com.niranzan.tutor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niranzan.tutor.constant.AppConstants;
import com.niranzan.tutor.service.UserService;
import com.niranzan.tutor.view.request.ResetPasswordRequestView;
import com.niranzan.tutor.view.response.SimpleResponseEntity;

import io.swagger.annotations.Api;

/**
 * @author Niranjan
 *
 */

@Api(value = "Password Reset", description = "Operations pertaining to reset account.")
@RestController
@RequestMapping("/password")
public class PasswordResetController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/send")
	public ResponseEntity<SimpleResponseEntity> sendResetLink(@RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response) {
		return ResponseEntity.ok()
				.body(new SimpleResponseEntity(HttpStatus.OK.value(), AppConstants.SUCCESS_RESPONSE_MSG, this.userService.generateResetLink(email)));
	}
	
	@PostMapping("/validate")
	public ResponseEntity<SimpleResponseEntity> validateResetLink(@RequestParam("token") String token, HttpServletRequest request, HttpServletResponse response) {
		boolean isValid = this.userService.validateResetLink(token);
		SimpleResponseEntity responseEntity = new SimpleResponseEntity();
		responseEntity.setData(null);
		responseEntity.setStatusMessage(isValid ? AppConstants.FAILURE_RESPONSE_MSG : AppConstants.SUCCESS_RESPONSE_MSG);
		responseEntity.setStatusCode(isValid ? HttpStatus.NOT_FOUND.value() : HttpStatus.OK.value());
		return ResponseEntity.ok()
				.body(responseEntity);
	}
	
	@PostMapping("/reset")
	public ResponseEntity<SimpleResponseEntity> resetPassword(@RequestBody ResetPasswordRequestView requestView, HttpServletRequest request, HttpServletResponse response) {
		boolean isReset = this.userService.resetPassword(requestView);
		SimpleResponseEntity responseEntity = new SimpleResponseEntity();
		responseEntity.setData(null);
		responseEntity.setStatusMessage(isReset ? AppConstants.FAILURE_RESPONSE_MSG : AppConstants.SUCCESS_RESPONSE_MSG);
		responseEntity.setStatusCode(isReset ? HttpStatus.BAD_REQUEST.value() : HttpStatus.OK.value());
		return ResponseEntity.ok()
				.body(responseEntity);
	}
}