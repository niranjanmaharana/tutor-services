package com.niranzan.tutor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.niranzan.tutor.model.UserProfile;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsername(String username);
    Optional<UserProfile> findByEmail(String email);
    
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByMobile(String mobile);
    
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM UserProfile u WHERE u.email = :email and u.id != :id")
    Boolean existsByEmailExceptUser(@Param("id") Long id, @Param("email") String email);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM UserProfile u WHERE u.mobile = :mobile and u.id != :id")
    Boolean existsByMobileExceptUser(@Param("id") Long id, @Param("mobile") String mobile);
}