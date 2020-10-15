/**
 * 
 */
package com.niranzan.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Niranjan
 *
 */

@Entity
@Table(name = "APP_PROPERTY", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "PRPRTY_KEY" }, name = "UNIQUE_PRPRTY_KEY")})
public class AppPrprty extends BaseEntity {
	@Column(name = "PRPRTY_KEY")
	private String prprtyKey;
	@Column(name = "PRPRTY_VALUE")
	private String prprtyValue;
	@Column(name = "PRPRTY_DESC")
	private String prprtyDesc;
	
	public AppPrprty() {
		super();
	}

	/**
	 * @return the prprtyKey
	 */
	public String getPrprtyKey() {
		return prprtyKey;
	}

	/**
	 * @param prprtyKey the prprtyKey to set
	 */
	public void setPrprtyKey(String prprtyKey) {
		this.prprtyKey = prprtyKey;
	}

	/**
	 * @return the prprtyValue
	 */
	public String getPrprtyValue() {
		return prprtyValue;
	}

	/**
	 * @param prprtyValue the prprtyValue to set
	 */
	public void setPrprtyValue(String prprtyValue) {
		this.prprtyValue = prprtyValue;
	}

	/**
	 * @return the prprtyDesc
	 */
	public String getPrprtyDesc() {
		return prprtyDesc;
	}

	/**
	 * @param prprtyDesc the prprtyDesc to set
	 */
	public void setPrprtyDesc(String prprtyDesc) {
		this.prprtyDesc = prprtyDesc;
	}
}