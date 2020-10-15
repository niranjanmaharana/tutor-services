package com.niranzan.tutor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.niranzan.tutor.enums.UserRoleEnum;
import com.niranzan.tutor.model.Authority;


@Repository
public interface RoleRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByName(UserRoleEnum roleNm);
}