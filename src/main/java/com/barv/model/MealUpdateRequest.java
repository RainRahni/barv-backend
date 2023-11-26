package com.barv.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealUpdateRequest {
    private Meal newMeal;
    private Meal currentMeal;
}
