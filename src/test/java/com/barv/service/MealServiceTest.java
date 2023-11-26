package com.barv.service;

import com.barv.model.Food;
import com.barv.repository.MealRepository;
import com.barv.model.Meal;
import com.barv.model.MealFoods;
import com.barv.model.MealType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class MealServiceTest {
    @Autowired
    private MealService mealService;
    @MockBean
    private MealRepository mealRepository;
    private Meal meal;
    private Food foodOne;
    private Food foodTwo;
    private MealFoods mealFoods;


    @BeforeEach
    void setUp() {
        foodOne = Food.builder()
                .protein(2)
                .fats(2)
                .name("testOne")
                .carbohydrates(12)
                .weightInGrams(9)
                .calories(200)
                .id(1L)
                .build();
        foodTwo = Food.builder()
                .protein(2)
                .fats(2)
                .name("testTwo")
                .carbohydrates(12)
                .weightInGrams(9)
                .calories(200)
                .id(2L)
                .build();
        meal = Meal.builder()
                .mealId(1L)
                .foods(List.of(foodOne, foodTwo))
                .calories(400)
                .carbohydrates(24)
                .fats(4)
                .type(MealType.BREAKFAST)
                .protein(4)
                .name("Breakfast-400")
                .build();
    }
    @Test
    void test() {
        assertEquals("1", 1 ,1);
    }

}