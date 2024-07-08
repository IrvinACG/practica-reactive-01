package com.iacg.app.app.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iacg.app.app.repositories.IProductoRepository;
import com.iacg.app.app.services.IProductService;
import com.iacg.app.app.services.dtos.ProductDTO;
import com.iacg.app.app.services.mapper.ProductMapper;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService{
	
	private final IProductoRepository repository;

	private final ProductMapper mapper;
	
	public ProductServiceImpl(IProductoRepository repository, ProductMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	public Mono<ProductDTO> save(ProductDTO dto) {
		return repository.save(mapper.toEntity(dto))
				.flatMap(product -> Mono.just(mapper.toDto(product)));
	}

	@Override
	public Mono<List<ProductDTO>> findAll() {
		return repository.findAll()
				.collectList()
				.flatMap(users -> {
					List<ProductDTO> usersDTO = users.stream()
							.map(mapper::toDto)
							.toList();
					return Mono.just(usersDTO);
				});
	}

	@Override
	public Mono<ProductDTO> findById(Long id) {
		return repository.findById(id)
				.flatMap(product -> Mono.just(mapper.toDto(product)));
	}

	@Override
	public Mono<ProductDTO> update(Long id, ProductDTO dto) {
		return repository.findById(id)
				.flatMap(product -> repository.save(mapper.update(product, dto)))
				.flatMap(product -> Mono.just(mapper.toDto(product)));
	}

	@Override
	public Mono<Void> delete(Long id) {
		return repository.deleteById(id);
	}

}
