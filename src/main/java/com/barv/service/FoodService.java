package com.barv.service;

import com.barv.model.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService {
    Food getFoodById(Long foodId) throws FoodNotFoundException;
    Food addFood(Food food) throws FoodAlreadyInDatabaseException;
    List<Food> findAllFoods();
    String removeFood(Long foodId) throws FoodNotFoundException;
    String updateFood(Long foodId, Food food) throws FoodNotFoundException, FoodAlreadyInDatabaseException;
}
