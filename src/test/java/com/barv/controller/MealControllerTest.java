package com.barv.controller;

import com.barv.model.Food;
import com.barv.repository.FoodRepository;
import com.barv.repository.MealRepository;
import com.barv.service.FoodServiceImpl;
import com.barv.service.MealFoodsServiceImpl;
import com.barv.service.MealServiceImpl;
import com.barv.model.Meal;
import com.barv.model.MealFoods;
import com.barv.model.MealType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MealController.class)
class MealControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private MealServiceImpl mealService;
    @MockBean
    private FoodServiceImpl foodService;
    @MockBean
    private MealFoodsServiceImpl mealFoodsService;
    @MockBean
    private MealRepository mealRepository;
    @MockBean
    private FoodRepository foodRepository;
    private Meal meal;
    private Food foodOne;
    private Food foodTwo;
    private MealFoods mealFoodsOne;
    private MealFoods mealFoodsTwo;


    @BeforeEach
    void setUp() {
        foodOne = Food.builder()
                .protein(2)
                .fats(2)
                .name("testOne")
                .carbohydrates(12)
                .weightInGrams(9)
                .calories(200)
                .Id(1L)
                .build();
        foodTwo = Food.builder()
                .protein(2)
                .fats(2)
                .name("testTwo")
                .carbohydrates(12)
                .weightInGrams(9)
                .calories(200)
                .Id(2L)
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
    void whenNothingInDatabase_thenAddFoodMealMealFoodsToDatabase() throws Exception {
            foodOne = Food.builder()
                    .protein(2)
                    .fats(2)
                    .name("testOne")
                    .carbohydrates(12)
                    .weightInGrams(9)
                    .calories(200)
                    .Id(1L)
                    .build();
            foodTwo = Food.builder()
                    .protein(2)
                    .fats(2)
                    .name("testTwo")
                    .carbohydrates(12)
                    .weightInGrams(9)
                    .calories(200)
                    .Id(2L)
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
        mealFoodsOne = MealFoods.builder()
                .food(foodOne)
                .meal(meal)
                .weight(foodOne.getWeightInGrams())
                .build();
        mealFoodsTwo = MealFoods.builder()
                .food(foodTwo)
                .meal(meal)
                .weight(foodOne.getWeightInGrams())
                .build();
        Mockito.when(foodService.addFood(foodOne)).thenReturn(foodOne);
        Mockito.when(foodService.addFood(foodTwo)).thenReturn(foodTwo);
        Mockito.when(mealService.addMeal(meal)).thenReturn(meal);
        Mockito.when(mealFoodsService.addMealFood(mealFoodsTwo)).thenReturn(mealFoodsTwo);
        Mockito.when(mealFoodsService.addMealFood(mealFoodsOne)).thenReturn(mealFoodsOne);

        mockMvc.perform(post("/api/v1/meal/addMeal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"mealId\": 1,\n" +
                        "    \"name\": \"Breakfast-400\",\n" +
                        "    \"calories\": 400,\n" +
                        "    \"protein\": 4,\n" +
                        "    \"carbohydrates\": 24,\n" +
                        "    \"fats\": 4,\n" +
                        "    \"type\": \"BREAKFAST\",\n" +
                        "    \"foods\": [\n" +
                        "        {\n" +
                        "            \"Id\": 1,\n" +
                        "            \"name\": \"testOne\",\n" +
                        "            \"calories\": 200,\n" +
                        "            \"protein\": 2,\n" +
                        "            \"carbohydrates\": 12,\n" +
                        "            \"fats\": 2,\n" +
                        "            \"weightInGrams\": 9\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"Id\": 2,\n" +
                        "            \"name\": \"testTwo\",\n" +
                        "            \"calories\": 200,\n" +
                        "            \"protein\": 2,\n" +
                        "            \"carbohydrates\": 12,\n" +
                        "            \"fats\": 2,\n" +
                        "            \"weightInGrams\": 9\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}\n"))
                .andExpect(status().isOk());
        Optional<Food> foodOneOptional = foodRepository.findById(1L);
        assertTrue(foodOneOptional.isPresent());
        assertEquals(foodOne, foodOneOptional.get());

        Optional<Food> foodTwoOptional = foodRepository.findById(2L);
        assertTrue(foodTwoOptional.isPresent());
        assertEquals(foodTwo, foodTwoOptional.get());

        Optional<Meal> mealOptional = mealRepository.findById(1L);
        assertTrue(mealOptional.isPresent());
        assertEquals(meal, mealOptional.get());



    }
}