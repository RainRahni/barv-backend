package com.barv.repository;

import com.barv.model.Meal;
import com.barv.model.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByType(MealType correspondingMealType);

    List<Meal> findByName(String mealName);

    boolean existsByName(String mealName);
}
