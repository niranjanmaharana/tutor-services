/**
 * 
 */
package com.niranzan.tutor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.niranzan.tutor.model.UserSession;

/**
 * @author Niranjan
 *
 */
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
	@Query("SELECT u FROM UserSession u WHERE u.userId = :userId")
	List<UserSession> findByUserId(@Param("userId") int userId);
	@Query("SELECT u FROM UserSession u WHERE u.username = :username")
	List<UserSession> findByUsername(@Param("username") String username);
}