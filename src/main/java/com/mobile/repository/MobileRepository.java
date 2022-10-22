package com.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.mobile.model.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Integer>{
	
	public List<Mobile> findAllByBrandIgnoreCase(String brand);	
	
	public List<Mobile> findAllByColorIgnoreCase(String color);
	
	public List<Mobile> findAllByModelAndColorIgnoreCase(int model,String color);
	
	public List<Mobile> findAllByProcessorIgnoreCase(String processor);
	
//	@Query(value = "select * from mobile where price between :value1 and :value2",nativeQuery = true)
//	public List<Mobile> findByPriceRange(@Param("value1") int value1,@Param("value2") int value2);

	public List<Mobile> findAllByPriceBetween(float from,float to);
	
	public List<Mobile> findAllByPriceGreaterThan(float price);
	
	public List<Mobile> findAllByCameraGreaterThanEqual(int camera);
	
}
