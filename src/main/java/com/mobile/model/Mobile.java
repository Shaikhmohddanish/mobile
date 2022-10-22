package com.mobile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mobile {
	
	@Id
	private int imei;
	
	@Column(nullable = false)
	private int model;

	@Column(nullable = false)
	private String brand;
	
	private String color;
	
	private float price;
	
	private String processor;
	
	private int camera;
	
}
