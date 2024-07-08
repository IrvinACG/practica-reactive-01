package com.iacg.app.app.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacg.app.app.repositories.IUserRepository;
import com.iacg.app.app.services.IUserService;
import com.iacg.app.app.services.dtos.UserDTO;
import com.iacg.app.app.services.mapper.UserMapper;

import reactor.core.publisher.Mono;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	private final IUserRepository repository;
	
	private final UserMapper mapper;

	public UserServiceImpl(IUserRepository repository, UserMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Mono<UserDTO> save(UserDTO dto) {
		return repository.save(mapper.toEntity(dto))
				.flatMap(user -> Mono.just(mapper.toDto(user)));
	}

	@Override
	public Mono<List<UserDTO>> findAll() {
		return repository.findAll()
				.collectList()
				.flatMap(users -> {
					List<UserDTO> userDTO = users.stream()
							.map(mapper::toDto)
							.toList();
					return Mono.just(userDTO);
				});
	}

	@Override
	public Mono<UserDTO> findById(Long id) {
		return repository.findById(id)
				.flatMap(user -> Mono.just(mapper.toDto(user)));
	}

	@Override
	public Mono<UserDTO> update(Long id, UserDTO dto) {
		return repository.findById(id)
				.flatMap(user -> repository.save(mapper.update(user, dto)))
				.flatMap(user -> Mono.just(mapper.toDto(user)));
	}

	@Override
	public Mono<Void> delete(Long id) {
		return repository.deleteById(id);
	}

	@Override
	public Mono<Long> count() {
		return repository.count();
	}

}
