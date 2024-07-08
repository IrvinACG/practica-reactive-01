package com.iacg.app.app.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacg.app.app.repositories.IAddressRepository;
import com.iacg.app.app.repositories.entities.Address;
import com.iacg.app.app.services.IAddressService;
import com.iacg.app.app.services.dtos.AddressDTO;
import com.iacg.app.app.services.mapper.AddressMapper;

import reactor.core.publisher.Mono;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService{

	private final IAddressRepository repository;
	
	private final AddressMapper mapper;
	
	public AddressServiceImpl(IAddressRepository repository, AddressMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	public Mono<AddressDTO> save(AddressDTO dto) {
		return repository.save(mapper.toEntity(dto))
				.flatMap(adddress -> Mono.just(mapper.toDto(adddress)));
	}

	@Override
	public Mono<List<AddressDTO>> findAll(Long user) {
		Mono<List<Address>> list = null;
		if(Objects.nonNull(user)) {
			list = repository.findByUser(user).collectList();
		}else {
			list = repository.findAll().collectList();
		}
		
		return list.flatMap(addresses -> {
					List<AddressDTO> addressesDTO = addresses.stream()
							.map(mapper::toDto)
							.toList();
					return Mono.just(addressesDTO);
				});
	}

	@Override
	public Mono<AddressDTO> findById(Long id) {
		return repository.findById(id)
				.flatMap(address -> Mono.just(mapper.toDto(address)));
	}

	@Override
	public Mono<AddressDTO> update(Long id, AddressDTO dto) {
		return repository.findById(id)
				.flatMap(address -> repository.save(mapper.update(address, dto)))
				.flatMap(address -> Mono.just(mapper.toDto(address)));
	}

	@Override
	public Mono<Void> delete(Long id) {
		return repository.deleteById(id);
	}

}
