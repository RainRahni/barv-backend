package com.barv.service;

import com.barv.model.Meal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealService {
    Meal addMeal(Meal meal);

    List<String> getExistingNextMealNames(String mealTime);

    Meal getMealWithGivenName(String mealName);

    Meal updateExistingMeal(Meal currentMeal, Meal newMeal);
}
