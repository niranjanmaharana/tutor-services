/**
 * 
 */
package com.niranzan.tutor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niranzan.tutor.constant.AppConstants;
import com.niranzan.tutor.exceptions.DuplicateFieldException;
import com.niranzan.tutor.exceptions.InvalidFormatException;
import com.niranzan.tutor.exceptions.ResourceNotFoundException;
import com.niranzan.tutor.model.UserProfile;
import com.niranzan.tutor.security.jwt.JwtProvider;
import com.niranzan.tutor.service.UserService;
import com.niranzan.tutor.view.request.UserRequestView;
import com.niranzan.tutor.view.response.JwtResponse;
import com.niranzan.tutor.view.response.SimpleResponseEntity;
import com.niranzan.tutor.view.response.UserResponseView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Niranjan
 *
 */

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('SUPERADMIN')")
@Api(value = "User", description = "Operations pertaining to user in CMS application", consumes = "application/json", produces = "application/json")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtProvider jwtProvider;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "Get all users in this application")
	@GetMapping
	public ResponseEntity<SimpleResponseEntity> findAll() {
		List<UserResponseView> userViews = userService.findAll();
		return new ResponseEntity<SimpleResponseEntity>(
				new SimpleResponseEntity(HttpStatus.OK.value(), "Successfully fetched all user types.", userViews),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Add a new user")
	@PostMapping
	public ResponseEntity<SimpleResponseEntity> registerUser(@Valid @RequestBody UserRequestView request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		LOGGER.info(username + " trying to register user.");
		try {
			UserProfile user = userService.save(request);
			request.setId(user.getId());
			LOGGER.info(username + " registered user successfully.");
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.OK.value(), "User registered successfully!", request));
		} catch (DuplicateFieldException exception) {
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), ""));
		} catch (InvalidFormatException exception) {
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), ""));
		} catch (Exception exception) {
			return ResponseEntity.ok().body(
					new SimpleResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error !", ""));
		}
	}

	@ApiOperation(value = "Update an existing user")
	@PutMapping
	public ResponseEntity<SimpleResponseEntity> updateUser(@Valid @RequestBody UserRequestView request) {
		LOGGER.info("User trying to update " + request.getUsername());
		try {
			userService.update(request);
		} catch (ResourceNotFoundException exception) {
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.NOT_FOUND.value(), exception.getMessage(), ""));
		} catch (DuplicateFieldException exception) {
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), ""));
		}
		LOGGER.info("User updated successfully...");
		return ResponseEntity.ok()
				.body(new SimpleResponseEntity(HttpStatus.OK.value(), "User updated successfully!", ""));
	}
	
	@ApiOperation(value = "Update token")
	@PostMapping("/token")
	public ResponseEntity<?> updateToken(HttpServletRequest request) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication != null) {
				return ResponseEntity.ok()
						.body(new JwtResponse(jwtProvider.generateJwtToken(authentication), AppConstants.TOKEN_TYPE));
			} else {
				return ResponseEntity.ok()
						.body(new SimpleResponseEntity(HttpStatus.NOT_FOUND.value(), AppConstants.FAILURE_RESPONSE_MSG, HttpStatus.NOT_FOUND.getReasonPhrase()));
			}
		} catch (Exception exception) {
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.NOT_FOUND.value(), exception.getMessage(), ""));
		}
	}
}