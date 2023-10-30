package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.model.MealFoods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealFoodsService {
    MealFoods addMealFood(MealFoods mealFoods) throws FoodAlreadyInDatabaseException;
    List<MealFoods> deleteMealFoodsWithNullValues();
}
