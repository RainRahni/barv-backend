package com.barv.controller;

import com.barv.service.MealFoodsServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "mealFoods" )
public class MealFoodsController {
    private MealFoodsServiceImpl mealFoodsServiceImpl;
}