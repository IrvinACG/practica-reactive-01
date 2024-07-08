package com.iacg.app.app.services.mapper;

import org.springframework.stereotype.Component;

import com.iacg.app.app.repositories.entities.Product;
import com.iacg.app.app.services.dtos.ProductDTO;

@Component
public class ProductMapper {

	public ProductDTO toDto(Product entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		dto.setPrice(entity.getPrice());
		return dto;
	}
	
	public Product toEntity(ProductDTO dto) {
		Product entity = new Product();
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setPrice(dto.getPrice());
		return entity;
	}
	
	public Product update(Product entity, ProductDTO dto) {
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setPrice(dto.getPrice());
		return entity;
	}
}
