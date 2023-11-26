package com.barv.controller;

import com.barv.model.Food;
import com.barv.service.FoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "food" )
@RequiredArgsConstructor
public class FoodController {
    private final FoodServiceImpl foodService;
    @GetMapping(path = "/{foodId}")
    public Food getFoodWithId(@PathVariable("foodId") Long foodId) {
        return foodService.getFoodById(foodId);
    }
    @GetMapping(path = "/allFoods")
    public List<Food> getAllFoods() {
        return foodService.findAllFoods();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/addFood")
    public Food addNewFood(@RequestBody Food food) {
        return foodService.addFood(food);
    }
    @DeleteMapping(path = "/del{foodId}")
    public String deleteFood(@PathVariable("foodId") Long foodId) {
        return foodService.removeFood(foodId);
    }
    @PutMapping(path = "/upt{foodId}")
    public String updateFoodDetails(@PathVariable("foodId") Long foodId,
                           @RequestBody Food food) {
        return foodService.updateFood(foodId, food);
    }
}
