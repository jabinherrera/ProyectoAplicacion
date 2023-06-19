package com.mezza.app.services;


import com.mezza.app.models.Administrador;
import com.mezza.app.models.Restaurant;
import com.mezza.app.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServices {
    @Autowired
    RestaurantRepository restaurantRepository;

    public void guardarRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public void editarRestaurant(Restaurant restaurantInfo, Long id) throws  Exception{
        Restaurant restaurant = restaurantRepository.findById(id).get();
        restaurant.setNombre(restaurantInfo.getNombre());
        restaurant.setTelefono(restaurantInfo.getTelefono());
        restaurant.setEmail(restaurantInfo.getEmail());
        restaurant.setInstagram(restaurantInfo.getInstagram());
        restaurant.setFacebook(restaurantInfo.getFacebook());
        restaurantInfo.setWhatsapp(restaurantInfo.getWhatsapp());

        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> mostrarRestaurant() {
        return restaurantRepository.findAll();
    }
}
