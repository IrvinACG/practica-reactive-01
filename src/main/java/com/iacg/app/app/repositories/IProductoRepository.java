package com.iacg.app.app.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.iacg.app.app.repositories.entities.Product;

@Repository
public interface IProductoRepository extends R2dbcRepository<Product, Long>{

}
