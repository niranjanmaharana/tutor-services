/**
 * 
 */
package com.niranzan.tutor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.niranzan.tutor.constant.AppConstants;
import com.niranzan.tutor.model.UserSession;
import com.niranzan.tutor.repository.UserSessionRepository;
import com.niranzan.tutor.service.UserSessionService;
import com.niranzan.tutor.view.response.UserSessionResponseView;

/**
 * @author Niranjan
 *
 */

@Service
public class UserSessionServiceImpl implements UserSessionService {
	@Autowired
	private UserSessionRepository userSessionRepository;
	
	@Override
	public UserSession saveSession(UserSession session) {
		return userSessionRepository.save(session);
	}
	
	@Async
	@Override
	public void saveSessionAsync(String value) {
		UserSession session = new UserSession();
		String[] values = value.split(",");
		int idx = 0;
		session.setUsername(values[idx++]);
		String country = values[idx++];
		session.setCountry(StringUtils.isNotBlank(country) ? country : AppConstants.UNKNOWN_VAL);
		session.setUserAgent(values[idx++]);
		session.setOrigin(values[idx++]);
		session.setSuccess(true);
		userSessionRepository.save(session);
	}
	
	@Override
	public List<UserSession> findAllSession() {
		return userSessionRepository.findAll();
	}

	@Override
	public List<UserSessionResponseView> getSessionByUsername(String username) {
		List<UserSessionResponseView> responses = new ArrayList<>();
		userSessionRepository.findByUsername(username).forEach(session -> {
			responses.add(this.getResponseView(session));
		});
		return responses;
	}
	
	@Override
	public UserSession findLastSessionByUsername(String username) {
		return null;
	}
	
	@Override
	public UserSessionResponseView getResponseView(UserSession session) {
		UserSessionResponseView response = new UserSessionResponseView();
		response.setUserId(session.getUserId());
		response.setUsername(session.getUsername());
		response.setLoginDtm(session.getLoginDtm());
		response.setLogoutDtm(session.getLogoutDtm());
		response.setSuccess(session.isSuccess());
		response.setCause(session.getCause());
		response.setCountry(session.getCountry());
		response.setOrigin(session.getOrigin());
		response.setUserAgent(session.getUserAgent());
		return response;
	}
}