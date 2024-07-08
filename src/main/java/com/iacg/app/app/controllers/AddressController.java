package com.iacg.app.app.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iacg.app.app.services.IAddressService;
import com.iacg.app.app.services.dtos.AddressDTO;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	private final IAddressService service;
	
	public AddressController(IAddressService service) {
		this.service = service;
	}
	
	@PostMapping
	public Mono<ResponseEntity<AddressDTO>> save(@RequestBody AddressDTO dto){
		return service.save(dto)
				.flatMap(address -> {
					return Mono.just(ResponseEntity.created(URI.create("/api/addresses/" + address.getId()))
							.body(address));
				});
	}
	
	@GetMapping
	public Mono<ResponseEntity<List<AddressDTO>>> findAll(@RequestParam(name = "user", required = false) Long user){
		return service.findAll(user)
				.flatMap(addresses -> Mono.just(ResponseEntity.ok(addresses)));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<AddressDTO>> findById(@PathVariable(required = true, name = "id") Long id){
		return service.findById(id)
				.flatMap(address -> Mono.just(ResponseEntity.ok(address)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<AddressDTO>> update(@PathVariable(required = true, name = "id") Long id, @RequestBody AddressDTO dto){
		return service.update(id, dto)
				.flatMap(address -> {
					return Mono.just(ResponseEntity.created(URI.create("/api/addresses/" + address.getId()))
							.body(address));
				}).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable(required = true, name = "id") Long id){
		return service.delete(id)
				.thenReturn(ResponseEntity.noContent().build());
	}
	
}
