package com.iacg.app.app.repositories.entities;

import org.springframework.data.annotation.Id;
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
@Table("users")
public class User {

	@Id
	private Long id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
}
