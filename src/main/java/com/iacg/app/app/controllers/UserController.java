package com.iacg.app.app.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import com.iacg.app.app.services.IUserService;
import com.iacg.app.app.services.dtos.UserDTO;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final IUserService service;
	
	public UserController(IUserService service) {
		this.service = service;
	}
	
	@PostMapping
	public Mono<ResponseEntity<UserDTO>> save(@RequestBody UserDTO dto){
		return service.save(dto)
				.flatMap(user -> {
					return Mono.just(ResponseEntity.created(URI.create("/api/users/" + user.getId()))
							.body(user));
				});
	}
	
	@GetMapping
	public Mono<ResponseEntity<List<UserDTO>>> findAll(){
		return service.findAll()
				.flatMap(users -> Mono.just(ResponseEntity.ok(users)));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<UserDTO>> findById(@PathVariable(required = true, name = "id") Long id){
		return service.findById(id)
				.flatMap(user -> Mono.just(ResponseEntity.ok(user)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<UserDTO>> update(@PathVariable(required = true, name = "id") Long id, @RequestBody UserDTO dto){
		return service.update(id, dto)
				.flatMap(user -> {
					return Mono.just(ResponseEntity.created(URI.create("/api/users/" + user.getId()))
							.body(user));
				}).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable(required = true, name = "id") Long id){
		return service.delete(id)
				.thenReturn(ResponseEntity.noContent().build());
	}
	
}
