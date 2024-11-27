package com.capgem.harshit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgem.harshit.entities.Order;
import com.capgem.harshit.entities.Rating;
import com.capgem.harshit.exception.ResourceNotFoundException;
import com.capgem.harshit.repositories.OrderRepository;
import com.capgem.harshit.repositories.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private OrderRepository orderRepository;
    
    public Rating submitReview(int orderId, Rating rating) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        rating.setOrder(order);
        return ratingRepository.save(rating);
    }

    // Retrieve all reviews for a specific restaurant
    public List<Rating> getRatingsByRestaurant(int restaurantId) {
        return ratingRepository.findByRestaurant_RestaurantId(restaurantId);
    }


}
