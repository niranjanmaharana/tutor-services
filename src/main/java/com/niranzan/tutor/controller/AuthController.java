package com.niranzan.tutor.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niranzan.tutor.constant.AppConstants;
import com.niranzan.tutor.exceptions.DuplicateFieldException;
import com.niranzan.tutor.exceptions.InvalidFormatException;
import com.niranzan.tutor.exceptions.ResourceNotFoundException;
import com.niranzan.tutor.model.UserProfile;
import com.niranzan.tutor.model.UserSession;
import com.niranzan.tutor.security.jwt.JwtProvider;
import com.niranzan.tutor.service.UserService;
import com.niranzan.tutor.service.UserSessionService;
import com.niranzan.tutor.view.request.AuthRequest;
import com.niranzan.tutor.view.request.UserRequestView;
import com.niranzan.tutor.view.response.JwtResponse;
import com.niranzan.tutor.view.response.SimpleResponseEntity;

import io.swagger.annotations.Api;

@Api(value = "Authentication", description = "Operations pertaining to login and logout.")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private UserSessionService userSessionService;
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest loginRequest, @RequestHeader Map<String, String> headers, HttpServletRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		LOGGER.info("{} logged in.", authentication.getName());
		
		/*
		 * add an entry to the session table to keep track of user logins
		 * */
		Locale requestLocale = request.getLocale();		
		UserSession session = new UserSession();
		session.setUsername(authentication.getName());
		String country = requestLocale.getDisplayCountry();
		session.setCountry(StringUtils.isNotBlank(country) ? country : AppConstants.UNKNOWN_VAL);
		session.setUserAgent(headers.get("user-agent"));
		session.setOrigin(headers.get("origin"));
		session.setSuccess(true);
		userSessionService.saveSession(session);
		return ResponseEntity.ok(new JwtResponse(jwt));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SimpleResponseEntity> registerUser(@Valid @RequestBody UserRequestView request) {
		try {
			LOGGER.info("{} trying to register.", request.getUsername());
			request.setActive(false);
			UserProfile user = userService.save(request);
			request.setPassword(null);
			request.setId(user.getId());
			LOGGER.info("{} registered as a new user.", request.getUsername());
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.OK.value(), AppConstants.SUCCESS_RESPONSE_MSG, request));
		} catch (DuplicateFieldException | InvalidFormatException exception) {
			LOGGER.info(exception.getMessage());
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null));
		} catch (ResourceNotFoundException exception) {
			LOGGER.info(exception.getMessage());
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.NOT_FOUND.value(), exception.getMessage(), null));
		} catch (Exception exception) {
			LOGGER.info(exception.getMessage());
			return ResponseEntity.ok().body(
					new SimpleResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), null));
		}
	}
}