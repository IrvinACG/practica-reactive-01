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
import org.springframework.web.bind.annotation.RestController;

import com.iacg.app.app.services.IProductService;
import com.iacg.app.app.services.dtos.ProductDTO;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final IProductService service;
	
	public ProductController(IProductService service) {
		this.service = service;
	}
	
	@PostMapping
	public Mono<ResponseEntity<ProductDTO>> save(@RequestBody ProductDTO dto){
		return service.save(dto)
				.flatMap(product -> {
					return Mono.just(ResponseEntity.created(URI.create("/api/products/" + product.getId()))
							.body(product));
				});
	}
	
	@GetMapping
	public Mono<ResponseEntity<List<ProductDTO>>> findAll(){
		return service.findAll()
				.flatMap(products -> Mono.just(ResponseEntity.ok(products)));
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<ProductDTO>> findById(@PathVariable(name = "id", required = true) Long id){
		return service.findById(id)
				.flatMap(user -> Mono.just(ResponseEntity.ok(user)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<ProductDTO>> update(@PathVariable(name = "id", required = true) Long id, @RequestBody ProductDTO dto){
		return service.update(id, dto)
				.flatMap(product -> {
					return Mono.just(ResponseEntity.created(URI.create("/api/products/" + product.getId()))
							.body(product));
				}).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable(name = "id", required = true) Long id){
		return service.delete(id)
				.thenReturn(ResponseEntity.noContent().build());
	}
}
