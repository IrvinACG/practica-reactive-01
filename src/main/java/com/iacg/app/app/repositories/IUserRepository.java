package com.iacg.app.app.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.iacg.app.app.repositories.entities.User;

@Repository
public interface IUserRepository extends R2dbcRepository<User, Long>{

}
