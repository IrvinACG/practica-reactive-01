package com.iacg.app.app.services.mapper;

import org.springframework.stereotype.Component;

import com.iacg.app.app.repositories.entities.User;
import com.iacg.app.app.services.dtos.UserDTO;

@Component
public class UserMapper {

	public UserDTO toDto(User entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());
		return dto;
	}
	
	public User toEntity(UserDTO dto) {
		User entity = new User();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		return entity;
	}
	
	public User update(User entity, UserDTO dto) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		return entity;
	}
}
