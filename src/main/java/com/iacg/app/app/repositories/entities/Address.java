package com.iacg.app.app.repositories.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
@Table("address")
public class Address {

	@Id
	private Long id;
	
	private String street;
	
	private String number;
	
	private String state;
	
	@Column("zip_code")
	private String zipCode;
	
	private String country;
	
	@Column("user_id")
	private Long user;
}
