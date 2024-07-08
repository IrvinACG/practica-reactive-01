package com.iacg.app.app.services.dtos;

import java.util.Date;
import java.util.List;

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
public class OrderDTO {
	
	private Date date;
	
	private UserDTO user;
	
	private List<AddressDTO> addresses;
	
	private ProductDTO product;
	
}
