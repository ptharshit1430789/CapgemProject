package com.capgem.harshit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.harshit.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
