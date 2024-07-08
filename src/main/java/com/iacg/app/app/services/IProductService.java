package com.iacg.app.app.services;

import java.util.List;

import com.iacg.app.app.services.dtos.ProductDTO;

import reactor.core.publisher.Mono;

public interface IProductService {

	Mono<ProductDTO> save(ProductDTO dto);
	
	Mono<List<ProductDTO>> findAll();
	
	Mono<ProductDTO> findById(Long id);
	
	Mono<ProductDTO> update(Long id, ProductDTO dto);
	
	Mono<Void> delete(Long id);
	
}
