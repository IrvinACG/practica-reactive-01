package com.iacg.app.app.services.mapper;

import org.springframework.stereotype.Component;

import com.iacg.app.app.repositories.entities.Address;
import com.iacg.app.app.services.dtos.AddressDTO;

@Component
public class AddressMapper {

	public AddressDTO toDto(Address entity) {
		AddressDTO dto = new AddressDTO();
		dto.setId(entity.getId());
		dto.setStreet(entity.getStreet());
		dto.setNumber(entity.getNumber());
		dto.setState(entity.getState());
		dto.setZipCode(entity.getZipCode());
		dto.setCountry(entity.getCountry());
		dto.setUser(entity.getUser());
		return dto;
	}
	
	public Address toEntity(AddressDTO dto) {
		Address entity = new Address();
		entity.setStreet(dto.getStreet());
		entity.setNumber(dto.getNumber());
		entity.setState(dto.getState());
		entity.setZipCode(dto.getZipCode());
		entity.setCountry(dto.getCountry());
		entity.setUser(dto.getUser());
		return entity;
	}
	
	public Address update(Address entity, AddressDTO dto) {
		entity.setStreet(dto.getStreet());
		entity.setNumber(dto.getNumber());
		entity.setState(dto.getState());
		entity.setZipCode(dto.getZipCode());
		entity.setCountry(dto.getCountry());
		entity.setUser(dto.getUser());
		return entity;
	}
	
}
