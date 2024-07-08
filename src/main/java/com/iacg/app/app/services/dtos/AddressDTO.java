package com.iacg.app.app.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddressDTO {
	
	private Long id;
	
	private String street;
	
	private String number;
	
	private String state;
	
	private String zipCode;
	
	private String country;
	
	private Long user;
}
