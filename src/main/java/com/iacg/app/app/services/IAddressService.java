package com.iacg.app.app.services;

import java.util.List;

import com.iacg.app.app.services.dtos.AddressDTO;

import reactor.core.publisher.Mono;

public interface IAddressService {

	Mono<AddressDTO> save(AddressDTO dto);
	
	Mono<List<AddressDTO>> findAll(Long user);
	
	Mono<AddressDTO> findById(Long id);
	
	Mono<AddressDTO> update(Long id, AddressDTO dto);
	
	Mono<Void> delete(Long id);
}
