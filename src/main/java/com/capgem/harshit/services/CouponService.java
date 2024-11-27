package com.capgem.harshit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgem.harshit.entities.Coupon;
import com.capgem.harshit.entities.Order;
import com.capgem.harshit.entities.OrdersCoupons;
import com.capgem.harshit.exception.ResourceNotFoundException;
import com.capgem.harshit.repositories.CouponRepository;
import com.capgem.harshit.repositories.OrderRepository;
import com.capgem.harshit.repositories.OrdersCouponsRepository;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrdersCouponsRepository ordersCouponsRepository;

    // Retrieve all coupons
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    // Get details of a specific coupon
    public Coupon getCouponById(int couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
    }

    // Create a new coupon
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    // Delete a specific coupon
    public void deleteCoupon(int couponId) {
        couponRepository.deleteById(couponId);
    }

    // Apply a coupon to an order
//    public OrdersCoupons applyCouponToOrder(int orderId, int couponId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
//        Coupon coupon = couponRepository.findById(couponId)
//                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
//        OrdersCoupons ordersCoupons = new OrdersCouponsId(order, coupon);
//        return ordersCouponsRepository.save(ordersCoupons);
//    }
}

