package com.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.dto.ResponseDto;
import com.mobile.exception.MobileAlreadyExistsException;
import com.mobile.exception.MobileDoesNotExistsException;
import com.mobile.exception.PriceRangeException;
import com.mobile.model.Mobile;
import com.mobile.service.IMobileService;

@RestController
@RequestMapping("/mobile")
public class MobileController {

	@Autowired
	private IMobileService service;
	
	@PostMapping("/add")
	public ResponseEntity<Mobile> saveMobile(@RequestBody Mobile mobile) throws MobileAlreadyExistsException{
		return new ResponseEntity<Mobile>(service.addMobile(mobile),HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Mobile> updateMobile(@RequestBody Mobile mobile) throws MobileDoesNotExistsException{
		return new ResponseEntity<Mobile>(service.updateMobile(mobile),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/id/{imie}")
	public ResponseEntity<ResponseDto> deleteMobileById(@PathVariable int imie) throws MobileDoesNotExistsException{
		service.deleteMobileByImieNumber(imie);
		return new ResponseEntity<ResponseDto>(new ResponseDto("Successfully deleted Mobile with IMIE number "+imie),HttpStatus.OK);
	}
	
	@GetMapping("/id/{imie}")
	public ResponseEntity<Mobile> getMobileByImie(@PathVariable int imie) throws MobileDoesNotExistsException{
		return new ResponseEntity<Mobile>(service.getMobileById(imie),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Mobile>> getAllMobiles() throws MobileDoesNotExistsException{
		return new ResponseEntity<List<Mobile>>(service.getAllMobiles(),HttpStatus.OK);
	}
	
	@GetMapping("/range/{from}/{to}")
	public ResponseEntity<List<Mobile>> getAllMobilesFromTo(@PathVariable int from,@PathVariable int to) throws PriceRangeException{
		return new ResponseEntity<List<Mobile>>(service.getAllMobilesWithRange(from,to),HttpStatus.OK);
	}
	
	@GetMapping("/brand/{brand}")
	public ResponseEntity<List<Mobile>> getAllMobilesBasedOnBrand(@PathVariable String brand) throws MobileDoesNotExistsException{
		return new ResponseEntity<List<Mobile>>(service.getAllMobilesWithBrandName(brand),HttpStatus.OK);
 	}
	
	@GetMapping("/color/{color}")
	public ResponseEntity<List<Mobile>> getAllMobilesBasedOnColor(@PathVariable String color) throws MobileDoesNotExistsException{
		return new ResponseEntity<List<Mobile>>(service.getAllMobilesWithColor(color),HttpStatus.OK);
 	}
	
	@GetMapping("/modelcolor/{model}/{color}")
	public ResponseEntity<List<Mobile>> getAllMobilesBasedOnModelAndColor(@PathVariable int model,@PathVariable String color) throws MobileDoesNotExistsException{
		return new ResponseEntity<List<Mobile>>(service.getAllMobilesByModelAndColor(model, color),HttpStatus.OK);
 	}
	
	@GetMapping("/processor/{processor}")
	public ResponseEntity<List<Mobile>> getAllMobilesBasedOnProcessor(@PathVariable String processor) throws MobileDoesNotExistsException{
		return new ResponseEntity<List<Mobile>>(service.getAllMobilesByProcessor(processor),HttpStatus.OK);
 	}
	
	@GetMapping("/pricegt/{price}")
	public ResponseEntity<List<Mobile>> getAllMobilesBasedOnProcessor(@PathVariable float price) throws MobileDoesNotExistsException{
		return new ResponseEntity<List<Mobile>>(service.getAllMobilesByPriceGreaterThan(price),HttpStatus.OK);
 	}

	@GetMapping("/camera/{camera}")
	public ResponseEntity<List<Mobile>> getAllMobilesBasedOnCamera(@PathVariable int camera) throws MobileDoesNotExistsException{
		return new ResponseEntity<List<Mobile>>(service.getAllByCameraGreaterThanEqual(camera),HttpStatus.OK);
 	}
	
}
