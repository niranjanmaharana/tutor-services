/**
 * 
 */
package com.niranzan.tutor.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niranzan.tutor.constant.AppConstants;
import com.niranzan.tutor.exceptions.DuplicateFieldException;
import com.niranzan.tutor.exceptions.ResourceNotFoundException;
import com.niranzan.tutor.model.AppPrprty;
import com.niranzan.tutor.service.AppPrprtyService;
import com.niranzan.tutor.view.request.AppPrprtyRequestView;
import com.niranzan.tutor.view.response.SimpleResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Niranjan
 *
 */

@Api(value = "Application Property", consumes = "application/json", produces = "application/json")
@PreAuthorize("hasRole('SUPERADMIN')")
@RestController
@RequestMapping("/property")
public class AppPrprtyController {
	@Autowired
	private AppPrprtyService appPrprtyService;
	private static final Logger LOGGER = LoggerFactory.getLogger(AppPrprtyController.class);
	
	@ApiOperation(value = "Get all application properties")
	@GetMapping
	public ResponseEntity<SimpleResponseEntity> findAll() {
		SimpleResponseEntity responseEntity = new SimpleResponseEntity();
		try {
			responseEntity.setData(appPrprtyService.findAll());
			responseEntity.setStatusMessage(AppConstants.SUCCESS_RESPONSE_MSG);
			responseEntity.setStatusCode(AppConstants.SUCCESS_RSPNS_STTS.value());
		} catch (Exception exception) {
			LOGGER.info(exception.getMessage());
			responseEntity.setData(null);
			responseEntity.setStatusMessage(AppConstants.FAILURE_RESPONSE_MSG);
			responseEntity.setStatusCode(AppConstants.FAILURE_RSPNS_STTS.value());
		}
		return ResponseEntity.ok(responseEntity);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SimpleResponseEntity> findAll(@PathVariable("id") long id) {
		SimpleResponseEntity responseEntity = new SimpleResponseEntity();
		try {
			responseEntity.setData(appPrprtyService.findById(id));
			responseEntity.setStatusMessage(AppConstants.SUCCESS_RESPONSE_MSG);
			responseEntity.setStatusCode(AppConstants.SUCCESS_RSPNS_STTS.value());
		} catch (Exception exception) {
			LOGGER.info(exception.getMessage());
			responseEntity.setData(null);
			responseEntity.setStatusMessage(AppConstants.FAILURE_RESPONSE_MSG);
			responseEntity.setStatusCode(AppConstants.FAILURE_RSPNS_STTS.value());
		}
		return ResponseEntity.ok(responseEntity);
	}
	
	@ApiOperation(value = "Add a new property")
	@PostMapping
	public ResponseEntity<SimpleResponseEntity> registerUser(@Valid @RequestBody AppPrprtyRequestView request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		LOGGER.info(username + " trying to save new property:{}", request.getPrprtyKey());
		try {
			AppPrprty property = appPrprtyService.save(request);
			request.setId(property.getId());
			LOGGER.info(username + " saved new property successfully:{}", request.getPrprtyKey());
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.OK.value(), AppConstants.SUCCESS_RESPONSE_MSG, request));
		} catch (DuplicateFieldException exception) {
			LOGGER.info(exception.getMessage());
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null));
		} catch (Exception exception) {
			LOGGER.info(exception.getMessage());
			return ResponseEntity.ok().body(
					new SimpleResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error !", null));
		}
	}
	
	@ApiOperation(value = "Update an existing property")
	@PutMapping
	public ResponseEntity<SimpleResponseEntity> updateUser(@Valid @RequestBody AppPrprtyRequestView request) {
		LOGGER.info("User trying to update " + request.getPrprtyKey());
		try {
			appPrprtyService.update(request);
		} catch (ResourceNotFoundException exception) {
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request));
		} catch (DuplicateFieldException exception) {
			return ResponseEntity.ok()
					.body(new SimpleResponseEntity(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request));
		}
		LOGGER.info("Property updated successfully");
		return ResponseEntity.ok()
				.body(new SimpleResponseEntity(HttpStatus.OK.value(), AppConstants.SUCCESS_RESPONSE_MSG, request));
	}
}