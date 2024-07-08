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
public class UserDTO {

	private Long id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
}
