/**
 * 
 */
package com.niranzan.tutor.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niranzan.tutor.enums.UserRoleEnum;
import com.niranzan.tutor.exceptions.DuplicateFieldException;
import com.niranzan.tutor.exceptions.InvalidFormatException;
import com.niranzan.tutor.exceptions.ResourceNotFoundException;
import com.niranzan.tutor.model.Authority;
import com.niranzan.tutor.model.ResetLink;
import com.niranzan.tutor.model.UserProfile;
import com.niranzan.tutor.repository.ResetLinkRepository;
import com.niranzan.tutor.repository.RoleRepository;
import com.niranzan.tutor.repository.UserRepository;
import com.niranzan.tutor.service.PasswordValidator;
import com.niranzan.tutor.service.UserService;
import com.niranzan.tutor.util.CommonUtil;
import com.niranzan.tutor.view.request.ResetPasswordRequestView;
import com.niranzan.tutor.view.request.UserRequestView;
import com.niranzan.tutor.view.response.UserResponseView;

/**
 * @author Niranjan
 *
 */

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordValidator passwordValidator;
	@Autowired
	private ResetLinkRepository resetLinkRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Value("${password.invalid.message}")
	private String invalidPasswordMessage;

	@Override
	@Transactional
	public List<UserResponseView> findAll() {
		List<UserProfile> users = userRepository.findAll();
		List<UserResponseView> userViews = new ArrayList<>();
		users.forEach(user -> userViews.add(getResponseView(user)));
		return userViews;
	}

	@Override
	@Transactional
	public UserProfile save(UserRequestView request) {
		if (userRepository.existsByUsername(request.getUsername()))
			throw new DuplicateFieldException("Username already exists !");
		if (userRepository.existsByEmail(request.getEmail()))
			throw new DuplicateFieldException("Email already exists !");
		if (userRepository.existsByMobile(request.getMobile()))
			throw new DuplicateFieldException("Mobile number already registered !");
		if (!passwordValidator.isValid(request.getPassword()))
			throw new InvalidFormatException(invalidPasswordMessage);

		UserProfile user = new UserProfile(request.getFirstNm(), request.getLastNm(), request.getUsername(),
				request.getEmail(), request.getMobile(), request.getPassword(), request.getProfilePic());
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Set<String> roleSets = new HashSet<>();
		if (request.getRole() == null)
			roleSets.add(UserRoleEnum.ROLE_USER.getName());
		else
			roleSets = request.getRole();
		List<Authority> roles = new ArrayList<>();

		roleSets.forEach(role -> {
			Authority adminRole = roleRepository.findByName(UserRoleEnum.getById(Integer.parseInt(role) - 1))
					.orElseThrow(() -> new ResourceNotFoundException("User role(" + role + ") not found!"));
			roles.add(adminRole);
		});

		user.setAuthorities(roles);
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public UserProfile update(UserRequestView request) {
		UserProfile user = userRepository.findById(request.getId()).orElse(null);
		if (user == null)
			throw new ResourceNotFoundException("User not found with id : " + request.getId());
		if (userRepository.existsByEmailExceptUser(request.getId(), request.getEmail()))
			throw new DuplicateFieldException("Email already registered !");
		if (userRepository.existsByMobileExceptUser(request.getId(), request.getMobile()))
			throw new DuplicateFieldException("Mobile number already registered !");
		user.setFirstNm(request.getFirstNm());
		user.setLastNm(request.getLastNm());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		Set<String> strRoles = request.getRole();
		List<Authority> roles = new ArrayList<>();

		strRoles.forEach(role -> {
			Authority adminRole = roleRepository.findByName(UserRoleEnum.getById(Integer.parseInt(role) - 1))
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(adminRole);
		});
		user.setAuthorities(roles);
		return userRepository.save(user);
	}

	@Override
	public UserResponseView getResponseView(UserProfile user) {
		UserResponseView response = new UserResponseView();
		response.setId(user.getId());
		response.setCreatedDate(user.getCreatedDtm());
		response.setCreatedBy(user.getCreatedBy());
		response.setLastModifiedDate(user.getUpdatedDtm());
		response.setLastModifiedBy(user.getUpdatedBy());
		response.setEmail(user.getEmail());
		response.setMobile(user.getMobile());
		response.setRoles(user.getAuthorities());
		response.setUsername(user.getUsername());
		response.setActive(user.isActive());
		response.setProfilePic(user.getPrflPic());
		return response;
	}

	@Override
	public ResetLink generateResetLink(String email) {
		UserProfile profile = userRepository.findByEmail(email).orElse(null);
		if (profile == null)
			return null;
		ResetLink resetLink = this.resetLinkRepository.findByUserId(profile.getId()).orElse(new ResetLink());
		resetLink.setActive(true);
		resetLink.setUserId(profile.getId());
		resetLink.setValidFrom(new Date());
		/*
		 * Set token validity 3 hours from now
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, 3);
		resetLink.setValidTo(calendar.getTime());
		resetLink.setLink(CommonUtil.generateRandomToken());
		resetLink = this.resetLinkRepository.save(resetLink);
		return resetLink;
	}

	@Override
	public boolean validateResetLink(String token) {
		ResetLink resetLink = this.resetLinkRepository.findByLink(token).orElse(null);
		if (resetLink != null)
			return resetLink.getValidTo().compareTo(new Date()) > 0 && resetLink.isActive();
		return false;
	}

	@Override
	public boolean resetPassword(ResetPasswordRequestView request) {
		ResetLink resetLink = this.resetLinkRepository.findByLink(request.getToken()).orElse(null);
		boolean isValid = false;
		if (resetLink != null)
			isValid = resetLink.getValidTo().compareTo(new Date()) > 0 && resetLink.isActive();
		if (isValid) {
			UserProfile profile = this.userRepository.getOne(resetLink.getId());
			if (profile == null)
				throw new ResourceNotFoundException("Link is not valid!");
			if (!passwordValidator.isValid(request.getPassword()))
				throw new InvalidFormatException(invalidPasswordMessage);
			profile.setPassword(request.getPassword());
			this.userRepository.save(profile);
			resetLink.setActive(false);
			return true;
		} else
			throw new ResourceNotFoundException("Link is not valid!");
	}
}