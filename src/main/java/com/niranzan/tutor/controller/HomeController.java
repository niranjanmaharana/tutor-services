/**
 * 
 */
package com.niranzan.tutor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niranzan.tutor.view.response.SimpleResponseEntity;

/**
 * @author Niranjan
 *
 */

@RestController
@RequestMapping("/welcome")
public class HomeController {
	@GetMapping
	public ResponseEntity<?> welcome(HttpServletRequest request) {
		return ResponseEntity.ok(new SimpleResponseEntity(200, "Success", null));
	}
}