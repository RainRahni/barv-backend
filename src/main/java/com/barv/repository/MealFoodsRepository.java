package com.barv.repository;

import com.barv.model.MealFoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealFoodsRepository extends JpaRepository<MealFoods, Long> {
    List<MealFoods> findMealFoodsByName(String name);
}
