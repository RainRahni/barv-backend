package com.barv.service;

import com.barv.exception.ApplicationException;
import com.barv.model.Food;
import com.barv.repository.FoodRepository;
import com.barv.model.MealType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    public Food getFoodById(Long foodId)  {
        Optional<Food> potentialFood = foodRepository.findById(foodId);
        if (potentialFood.isEmpty()) {
            throw new ApplicationException("No food with this Id in the database!");
        }
        return potentialFood.get();
    }
    public Food addFood(Food food) {
        if (foodRepository.findAll().contains(food)) {
            throw new ApplicationException("Food is already in the database!");
        }
        foodRepository.save(food);
        return food;
    }
    public List<Food> findAllFoods() {
        return foodRepository.findAll();
    }
    public String removeFood(Long foodId) {
        boolean exists = foodRepository.existsById(foodId);
        if (!exists) {
            throw new ApplicationException("Food with given id does not exist!");
        }
        foodRepository.deleteById(foodId);
        return String.format("Food with id %d successfully removed from the database!", foodId);
    }
    /**
     * Update food with given id with given food`s attributes.
     * @param foodId what food will be modified.
     * @param food what attributes will be given to existing food.
     * @return whether updating was successful.
     */

    @Transactional
    public String updateFood(Long foodId, Food food) {
        Food existingFood = foodRepository
                .findById(foodId)
                .orElseThrow();
        if (!Objects.equals(food, existingFood)) {
            existingFood.setName(food.getName());
            existingFood.setProtein(food.getProtein());
            existingFood.setFats(food.getFats());
            existingFood.setWeightInGrams(food.getWeightInGrams());
            existingFood.setCarbohydrates(food.getCarbohydrates());
            existingFood.setCalories(food.getCalories());
            return "Successfully changed food`s details!";
        }
        throw new ApplicationException("Food objects are equal already!");
    }
}
