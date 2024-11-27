package com.capgem.harshit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.harshit.entities.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
