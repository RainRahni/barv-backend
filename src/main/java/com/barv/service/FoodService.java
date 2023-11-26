package com.barv.service;

import com.barv.model.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService {
    Food getFoodById(Long foodId);
    Food addFood(Food food);
    List<Food> findAllFoods();
    String removeFood(Long foodId);
    String updateFood(Long foodId, Food food);
}
