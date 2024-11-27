package com.capgem.harshit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.harshit.entities.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer>{

}
