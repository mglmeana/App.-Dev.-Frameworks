package com.frameworks.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.frameworks.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String>{

	@Query("INSERT INTO ORDERS (oId, oShop, oPayed)  VALUES(?, ?, ?);")
	public void insertOrderData(String id, String shop, boolean payed);

	@Query("INSERT INTO BELONGING (packageId, flowerId, flowerAmount) VALUES (?, ?, ?);")
	public void insertOrderContent(String id, String type, int amount);
}
