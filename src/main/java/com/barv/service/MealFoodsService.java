package com.barv.service;

import com.barv.model.MealFoods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealFoodsService {
    MealFoods addMealFood(MealFoods mealFoods);
    List<MealFoods> deleteMealFoodsWithNullValues();
}
