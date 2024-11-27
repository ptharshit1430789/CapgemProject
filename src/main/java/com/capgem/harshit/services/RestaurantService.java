package com.capgem.harshit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgem.harshit.entities.MenuItem;
import com.capgem.harshit.entities.Restaurant;
import com.capgem.harshit.exception.ResourceNotFoundException;
import com.capgem.harshit.repositories.MenuItemRepository;
import com.capgem.harshit.repositories.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
	
	public Restaurant getRestaurantById(int restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new ResourceNotFoundException("Restaurant not found with id: " + restaurantId);
        }
    }
	
	public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
	
	 public Restaurant updateRestaurant(int restaurantId, Restaurant updatedRestaurant) {
	        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
	        if (restaurant.isPresent()) {
	            Restaurant existingRestaurant = restaurant.get();
	            existingRestaurant.setRestaurantId(updatedRestaurant.getRestaurantId());
	            existingRestaurant.setRestaurantName(updatedRestaurant.getRestaurantName());
	            existingRestaurant.setRestaurantAddress(updatedRestaurant.getRestaurantAddress());
	            existingRestaurant.setRestaurantPhone(updatedRestaurant.getRestaurantPhone());
	            return restaurantRepository.save(existingRestaurant); 
	        } else {
	            throw new ResourceNotFoundException("Restaurant not found with id: " + restaurantId);
	        }
	    }
	 
	 public void deleteRestaurant(int restaurantId) {
	        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
	        if (restaurant.isPresent()) {
	            restaurantRepository.delete(restaurant.get());
	        } else {
	            throw new RuntimeException("Restaurant not found with id: " + restaurantId);
	        }
	    }
	 
//	 public List<MenuItem> getAllMenuItemsForRestaurant(int restaurantId) {
//	        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
//	        if (restaurant.isPresent()) {
//	            return menuItemRepository.findByRestaurants(restaurant.get());  // Return menu items for the restaurant
//	        } else {
//	            throw new ResourceNotFoundException("Restaurant not found with id: " + restaurantId);
//	        }
//	    }
	 
	 public List<MenuItem> getMenuItemsByRestaurant(int restaurantId) {
	        return menuItemRepository.findByRestaurant_RestaurantId(restaurantId);
	    }

	    // Add a new menu item to a specific restaurant
	    public MenuItem addMenuItemToRestaurant(int restaurantId, MenuItem menuItem) {
	        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
	        if (restaurant.isPresent()) {
	            menuItem.setRestaurant(restaurant.get());  // Set the restaurant for the menu item
	            return menuItemRepository.save(menuItem);  // Save the new menu item
	        } else {
	            throw new RuntimeException("Restaurant not found with id: " + restaurantId);
	        }
	    }

	    // Update details of a specific menu item in a restaurant
	    public MenuItem updateMenuItem(int restaurantId, int itemId, MenuItem updatedMenuItem) {
	        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
	        Optional<MenuItem> menuItem = menuItemRepository.findById(itemId);
	        if (restaurant.isPresent() && menuItem.isPresent() && menuItem.get().getRestaurant().getRestaurantId() == restaurantId) {
	            MenuItem existingMenuItem = menuItem.get();
	            existingMenuItem.setItemName(updatedMenuItem.getItemName());
	            existingMenuItem.setItemDescription(updatedMenuItem.getItemDescription());
	            existingMenuItem.setItemPrice(updatedMenuItem.getItemPrice());
	            return menuItemRepository.save(existingMenuItem);  // Save the updated menu item
	        } else {
	            throw new RuntimeException("Menu item or restaurant not found or mismatch");
	        }
	    }

	    // Delete a specific menu item from a restaurant
	    public void deleteMenuItem(int restaurantId, int itemId) {
	        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
	        Optional<MenuItem> menuItem = menuItemRepository.findById(itemId);
	        if (restaurant.isPresent() && menuItem.isPresent() && menuItem.get().getRestaurant().getRestaurantId() == restaurantId) {
	            menuItemRepository.delete(menuItem.get());  // Delete the menu item
	        } else {
	            throw new RuntimeException("Menu item or restaurant not found or mismatch");
	        }
	    }

}
