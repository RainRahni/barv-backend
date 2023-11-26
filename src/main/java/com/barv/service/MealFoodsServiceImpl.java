package com.barv.service;

import com.barv.model.MealFoods;
import com.barv.repository.MealFoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealFoodsServiceImpl implements MealFoodsService {
    private final MealFoodsRepository mealFoodsRepository;
    @Override
    public MealFoods addMealFood(MealFoods mealFoods) {
        if (mealFoods.getId() == null || !mealFoodsRepository.existsById(mealFoods.getId())) {
            mealFoodsRepository.save(mealFoods);
        }
        return mealFoods;
    }

    @Override
    public List<MealFoods> deleteMealFoodsWithNullValues() {
        List<MealFoods> mealFoodsWithNullValues = mealFoodsRepository.findMealFoodsByName(null);
        mealFoodsRepository.deleteAll(mealFoodsWithNullValues);
        return mealFoodsWithNullValues;
    }

}
