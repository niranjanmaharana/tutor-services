/**
 * 
 */
package com.niranzan.tutor.service;

import java.util.List;

import com.niranzan.tutor.model.UserSession;
import com.niranzan.tutor.view.response.UserSessionResponseView;

/**
 * @author Niranjan
 *
 */
public interface UserSessionService {
	public UserSession saveSession(UserSession session);
	public List<UserSession> findAllSession();
	public List<UserSessionResponseView> getSessionByUsername(String username);
	public UserSession findLastSessionByUsername(String username);
	public void saveSessionAsync(String values);
	public UserSessionResponseView getResponseView(UserSession session);
}