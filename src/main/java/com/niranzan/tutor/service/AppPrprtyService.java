/**
 * 
 */
package com.niranzan.tutor.service;

import java.util.List;

import com.niranzan.tutor.model.AppPrprty;
import com.niranzan.tutor.view.request.AppPrprtyRequestView;
import com.niranzan.tutor.view.response.AppPrprtyResponseView;

/**
 * @author Niranjan
 *
 */
public interface AppPrprtyService {
	public List<AppPrprty> getAppProperties();
	public AppPrprty getAppPropertyByKey(String key);
	public List<AppPrprtyResponseView> findAll();
	public AppPrprty save(AppPrprtyRequestView request);
	public AppPrprty update(AppPrprtyRequestView request);
	public AppPrprtyResponseView getResponseView(AppPrprty property);
	public AppPrprtyResponseView findById(long id);
}