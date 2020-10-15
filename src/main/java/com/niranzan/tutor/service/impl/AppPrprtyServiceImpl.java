/**
 * 
 */
package com.niranzan.tutor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niranzan.tutor.exceptions.DuplicateFieldException;
import com.niranzan.tutor.exceptions.ResourceNotFoundException;
import com.niranzan.tutor.model.AppPrprty;
import com.niranzan.tutor.repository.AppPrprtyRepository;
import com.niranzan.tutor.service.AppPrprtyService;
import com.niranzan.tutor.view.request.AppPrprtyRequestView;
import com.niranzan.tutor.view.response.AppPrprtyResponseView;

/**
 * @author Niranjan
 *
 */

@Service
public class AppPrprtyServiceImpl implements AppPrprtyService {
	@Autowired
	private AppPrprtyRepository prprtyRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(AppPrprtyServiceImpl.class);
	
	@Override
	public List<AppPrprty> getAppProperties() {
		return this.prprtyRepository.findAll();
	}
	
	@Override
	public List<AppPrprtyResponseView> findAll() {
		List<AppPrprtyResponseView> views = new ArrayList<>();
		this.prprtyRepository.findAll().forEach(property -> views.add(this.getResponseView(property)));
		return views;
	}
	
	@Override
	public AppPrprtyResponseView findById(long id) {
		return this.getResponseView(prprtyRepository.findById(id).orElse(null));
	}

	@Override
	public AppPrprty getAppPropertyByKey(String key) {
		return prprtyRepository.findByPrprtyKey(key).orElse(null);
	}

	@Override
	public AppPrprty save(AppPrprtyRequestView request) {
		if (prprtyRepository.existsByPrprtyKey(request.getPrprtyKey())) {
			throw new DuplicateFieldException("Property key('" + request.getPrprtyKey() + "') already exists!");
		}
		
		AppPrprty prprty = new AppPrprty();
		prprty.setPrprtyKey(request.getPrprtyKey());
		prprty.setPrprtyValue(request.getPrprtyValue());
		prprty.setPrprtyDesc(request.getPrprtyDesc());
		return prprtyRepository.save(prprty);
	}

	@Override
	public AppPrprty update(AppPrprtyRequestView request) {
		AppPrprty prprty = prprtyRepository.findById(request.getId()).orElse(null);
		if (prprty == null) {
			LOGGER.info("Property key already used:{}", request.getPrprtyKey());
			throw new ResourceNotFoundException("Property not found with id: {}" + request.getId());
		}
		if (prprtyRepository.existsByPrprtyKeyExceptThisPrprty(request.getId(), request.getPrprtyKey())) {
			LOGGER.info("Property key already used:{}", request.getPrprtyKey());
			throw new DuplicateFieldException("Property key already registered !");
		}
		prprty.setPrprtyKey(request.getPrprtyKey());
		prprty.setPrprtyValue(request.getPrprtyValue());
		prprty.setPrprtyDesc(request.getPrprtyDesc());
		return prprtyRepository.save(prprty);
	}
	
	@Override
	public AppPrprtyResponseView getResponseView(AppPrprty property) {
		AppPrprtyResponseView response = new AppPrprtyResponseView();
		response.setId(property.getId());
		response.setActive(property.isActive());
		response.setPrprtyKey(property.getPrprtyKey());
		response.setPrprtyValue(property.getPrprtyValue());
		response.setPrprtyDesc(property.getPrprtyDesc());
		response.setCreatedBy(property.getCreatedBy());
		response.setCreatedDtm(property.getCreatedDtm());
		response.setUpdatedBy(property.getUpdatedBy());
		response.setUpdatedDtm(property.getUpdatedDtm());
		return response;
	}
}