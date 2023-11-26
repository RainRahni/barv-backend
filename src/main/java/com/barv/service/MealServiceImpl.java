package com.barv.service;

import com.barv.exception.ApplicationException;
import com.barv.model.Food;
import com.barv.repository.FoodRepository;
import com.barv.repository.MealRepository;
import com.barv.model.Meal;
import com.barv.model.MealFoods;
import com.barv.model.MealType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final FoodService foodService;
    private final MealFoodsService mealFoodsService;
    private final FoodRepository foodRepository;
    @Override
    public Meal addMeal(Meal meal) {
        saveNewFoodsForMeal(meal.getFoods());
        mealRepository.save(meal);
        mealFoodsService.deleteMealFoodsWithNullValues();
        List<Food> foodsList = meal.getFoods();
        for (Food food: foodsList) {
            MealFoods mealFoods = new MealFoods();
            mealFoods.setMeal(meal);
            mealFoods.setFood(food);
            mealFoods.setWeight(food.getWeightInGrams());
            mealFoods.setName(food.getName());
            mealFoodsService.addMealFood(mealFoods);
        }
        return meal;
    }

    @Override
    public List<String> getExistingNextMealNames(String mealTime) {
        MealType correspondingMealType = MealType.valueOf(mealTime);
        List<Meal> mealsWithTypeInDatabase =
                mealRepository.findByType(correspondingMealType);
        return mealsWithTypeInDatabase.stream().map(Meal::getName).toList();
    }

    @Override
    public Meal getMealWithGivenName(String mealName) {
        if (mealRepository.existsByName(mealName)) {
            return mealRepository.findByName(mealName).get(0);
        }
        throw new ApplicationException("No meal with given name found!");
    }

    @Override
    public Meal updateExistingMeal(Meal currentMeal, Meal newMeal) {
        Meal mealFromDatabase = mealRepository.findById(currentMeal.getMealId()).get();
        if (currentMeal.equals(newMeal)) {
            throw new ApplicationException("Both meals are exactly the same!");
        }
        Meal updatedMeal = updateAttributesOfMeal(mealFromDatabase, newMeal);
        List<Food> foods = newMeal.getFoods();
        saveNewFoodsForMeal(foods);
        updatedMeal.addFoodsToList(foods);
        mealRepository.save(updatedMeal);
        return updatedMeal;
    }

    private Meal updateAttributesOfMeal(Meal currentMeal, Meal newMeal) {
        currentMeal.setName(newMeal.getName());
        currentMeal.setCalories(newMeal.getCalories());
        currentMeal.setCarbohydrates(newMeal.getCarbohydrates());
        currentMeal.setFats(newMeal.getFats());
        currentMeal.setProtein(newMeal.getProtein());
        currentMeal.setType(newMeal.getType());
        return currentMeal;
    }

    private void saveNewFoodsForMeal(List<Food> foodsToSave) {
        for (Food food : foodsToSave) {
            Optional<Food> foodInDatabase =
                    foodRepository.findByNameAndWeightInGrams(food.getName(), food.getWeightInGrams());
            if (foodInDatabase.isEmpty()) {
                foodService.addFood(food);
            }
        }
    }

}
