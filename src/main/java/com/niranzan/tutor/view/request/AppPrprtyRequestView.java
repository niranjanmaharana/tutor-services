/**
 * 
 */
package com.niranzan.tutor.view.request;

/**
 * @author Niranjan
 *
 */
public class AppPrprtyRequestView {
	private long id;
	private String prprtyKey;
	private String prprtyValue;
	private String prprtyDesc;
	private boolean active;
	
	public AppPrprtyRequestView() {
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
}