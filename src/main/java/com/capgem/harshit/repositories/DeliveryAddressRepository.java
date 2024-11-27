package com.capgem.harshit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.harshit.entities.DeliveryAddress;



public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Integer>{
//    List<DeliveryAddress> findByRestaurant_RestaurantId(int restaurantId);

}
