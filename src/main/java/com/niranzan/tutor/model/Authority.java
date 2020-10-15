/**
 * 
 */
package com.niranzan.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import com.niranzan.tutor.enums.UserRoleEnum;

/**
 * @author Niranjan
 *
 */

@Entity
@Table(name = "AUTHORITY", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "name" }, name = "UNIQUE_ROLENAME") })
public class Authority extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60)
	private UserRoleEnum name;

	public Authority() {
		super();
	}

	/**
	 * @param name
	 */
	public Authority(UserRoleEnum name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public UserRoleEnum getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(UserRoleEnum name) {
		this.name = name;
	}
}