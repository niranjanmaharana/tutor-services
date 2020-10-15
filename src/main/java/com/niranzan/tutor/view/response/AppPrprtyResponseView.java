/**
 * 
 */
package com.niranzan.tutor.view.response;

import java.util.Date;

/**
 * @author Niranjan
 *
 */
public class AppPrprtyResponseView {
	private long id;
	private String prprtyKey;
	private String prprtyValue;
	private String prprtyDesc;
	private Date createdDtm;
	private String createdBy;
	private Date updatedDtm;
	private String updatedBy;
	private boolean active;
	
	public AppPrprtyResponseView() {
		super();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the createdDtm
	 */
	public Date getCreatedDtm() {
		return createdDtm;
	}

	/**
	 * @param createdDtm the createdDtm to set
	 */
	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedDtm
	 */
	public Date getUpdatedDtm() {
		return updatedDtm;
	}

	/**
	 * @param updatedDtm the updatedDtm to set
	 */
	public void setUpdatedDtm(Date updatedDtm) {
		this.updatedDtm = updatedDtm;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}