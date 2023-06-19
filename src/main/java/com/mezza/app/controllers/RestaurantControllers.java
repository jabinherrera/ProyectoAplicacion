package com.mezza.app.controllers;

import com.mezza.app.models.Restaurant;
import com.mezza.app.repositories.RestaurantRepository;
import com.mezza.app.services.RestaurantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class RestaurantControllers {

    @Autowired
    RestaurantServices restaurantServices;

    @PutMapping ("/admin/mi_reataurant/editar/{id}")
    public void editarRestaurant(Restaurant restaurantInfo, @PathVariable Long id){

        try {
            restaurantServices.editarRestaurant(restaurantInfo,id);
        }catch (Exception e){
            System.err.println("Error al editar restaurante");
        }

    }



}
