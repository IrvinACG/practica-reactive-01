package com.iacg.app.app.services;

import java.util.List;

import com.iacg.app.app.services.dtos.UserDTO;

import reactor.core.publisher.Mono;

public interface IUserService {

	Mono<UserDTO> save(UserDTO dto);
	
	Mono<List<UserDTO>> findAll();
	
	Mono<UserDTO> findById(Long id);
	
	Mono<UserDTO> update(Long id, UserDTO dto);
	
	Mono<Void> delete(Long id);

	Mono<Long> count();
}
