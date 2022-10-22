package com.mobile.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mobile.model.Mobile;

@Service
public interface IMobileService {

	public Mobile addMobile(Mobile mobile);
	
	public Mobile updateMobile(Mobile mobile);
	
	public void deleteMobileByImieNumber(int imie);
	
	public Mobile getMobileById(int imie);
	
	public List<Mobile> getAllMobiles();
	
	public List<Mobile> getAllMobilesWithRange(float from,float to);
	
	public List<Mobile> getAllMobilesWithBrandName(String brand);
	
	public List<Mobile> getAllMobilesWithColor(String color);
	
	public List<Mobile> getAllMobilesByModelAndColor(int model,String color);
	
	public List<Mobile> getAllMobilesByProcessor(String processor);
	
	public List<Mobile> getAllMobilesByPriceGreaterThan(float price);

	public List<Mobile> getAllByCameraGreaterThanEqual(int camera);
	
}
