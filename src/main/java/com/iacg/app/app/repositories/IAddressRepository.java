package com.iacg.app.app.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.iacg.app.app.repositories.entities.Address;

import reactor.core.publisher.Flux;


@Repository
public interface IAddressRepository extends R2dbcRepository<Address, Long>{

	Flux<Address> findByUser(Long user);
}
