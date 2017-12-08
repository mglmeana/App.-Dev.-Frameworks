package com.frameworks.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.frameworks.entities.Flower;

@Repository
public interface FlowerRepository extends CrudRepository<Flower, String>{
	
}
