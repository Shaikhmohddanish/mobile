package com.mobile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.exception.MobileAlreadyExistsException;
import com.mobile.exception.MobileDoesNotExistsException;
import com.mobile.exception.PriceRangeException;
import com.mobile.model.Mobile;
import com.mobile.repository.MobileRepository;

@Service
public class MobileServiceImpl implements IMobileService{

	@Autowired
	private MobileRepository repo;

	@Override
	public Mobile addMobile(Mobile mobile) {
		Optional<Mobile> savedMobile = repo.findById(mobile.getImei());
		if(savedMobile.isPresent()) {
			throw new MobileAlreadyExistsException("Mobile already exists");
		}
		return repo.save(mobile);
	}

	@Override
	public Mobile updateMobile(Mobile mobile) {
		Optional<Mobile> savedMobile = repo.findById(mobile.getImei());
		if(savedMobile.isEmpty()) {
			throw new MobileDoesNotExistsException("Mobile not found with IMEI number "+mobile.getImei());
		}
		return repo.save(mobile);
	}

	@Override
	public void deleteMobileByImieNumber(int imie) {
		Optional<Mobile> savedMobile = repo.findById(imie);
		if(savedMobile.isEmpty()) {
			throw new MobileDoesNotExistsException("Mobile with IMIE number "+imie+" Does not exists");
		}
		repo.deleteById(imie);
	}

	@Override
	public Mobile getMobileById(int imie) {
		Optional<Mobile> saveMobile = repo.findById(imie);
		if(saveMobile.isEmpty()) {
			throw new MobileDoesNotExistsException("Mobile with IMIE number "+imie+" Does not exists");
		}
		return repo.findById(imie).get();
	}

	@Override
	public List<Mobile> getAllMobiles() {
		List<Mobile> mobileList = repo.findAll();
		if(mobileList.size()<1) {
			throw new MobileDoesNotExistsException("Sorry no mobiles are available");
		}
		return mobileList;
	}

	@Override
	public List<Mobile> getAllMobilesWithRange(float from, float to) {
		List<Mobile> mobileList = repo.findAllByPriceBetween(from,to);
		if(mobileList.size()<1) {
			throw new PriceRangeException("Invalid Price range please check again");
		}
		return mobileList;
	}

	@Override
	public List<Mobile> getAllMobilesWithBrandName(String brand) {
		List<Mobile> mobileList = repo.findAllByBrandIgnoreCase(brand);
		if(mobileList.size()<1) {
			throw new MobileDoesNotExistsException("No mobiles found with brand name "+brand);
		}
		return mobileList;
	}

	@Override
	public List<Mobile> getAllMobilesWithColor(String color) {
		List<Mobile> mobileList = repo.findAllByColorIgnoreCase(color);
		if(mobileList.size()<1) {
			throw new MobileDoesNotExistsException("No mobiles found with color "+color);
		}
		return mobileList;
	}

	@Override
	public List<Mobile> getAllMobilesByModelAndColor(int model, String color) {
		List<Mobile> mobileList = repo.findAllByModelAndColorIgnoreCase(model, color);
		if(mobileList.size()<1) {
			throw new MobileDoesNotExistsException("No mobiles found with model "+model+" and color "+color);
		}
		return mobileList;
	}

	@Override
	public List<Mobile> getAllMobilesByProcessor(String processor) {
		List<Mobile> mobileList = repo.findAllByProcessorIgnoreCase(processor);
		if(mobileList.size()<1) {
			throw new MobileDoesNotExistsException("No mobiles found with processor "+processor);
		}
		return mobileList;
	}

	@Override
	public List<Mobile> getAllMobilesByPriceGreaterThan(float price) {
		List<Mobile> mobileList = repo.findAllByPriceGreaterThan(price);
		if(mobileList.size()<1) {
			throw new MobileDoesNotExistsException("No mobiles found with price greater than "+price);
		}
		return mobileList;
	}
	
	@Override
	public List<Mobile> getAllByCameraGreaterThanEqual(int camera) {
		List<Mobile> mobileList = repo.findAllByCameraGreaterThanEqual(camera);
		if(mobileList.size()<1) {
			throw new MobileDoesNotExistsException("No mobiles found with camera greater than equal to "+camera+" MP");
		}
		return mobileList;
	}
	
	
	
}
