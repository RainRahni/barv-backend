package com.barv.service;

import com.barv.exception.ApplicationException;
import com.barv.model.Food;
import com.barv.repository.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FoodServiceTest {
    @Autowired
    private FoodService foodService;
    @MockBean
    private FoodRepository foodRepository;
    private Food food;
    @BeforeEach
    void setUp() {
        food = Food.builder()
                .name("awd")
                .protein(12)
                .calories(12)
                .fats(12)
                .weightInGrams(12)
                .carbohydrates(15)
                .id(1L)
                .build();
        Mockito.when(foodRepository.findById(1L)).thenReturn(Optional.of(food));
    }

    @Test
    void whenValidFoodId_thenFoodFound() {
        Long expectedId = 1L;
        Long actualId =
                foodService.getFoodById(expectedId).getId();
        assertEquals(expectedId, actualId);
    }
    @Test
    void whenFoodIdNotValid_thenThrowsException() {
        Long expectedId = 100L;
        Exception exception = assertThrows(ApplicationException.class, () -> foodService.getFoodById(expectedId));
        String expectedMessage = "No food with this Id in the database!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}