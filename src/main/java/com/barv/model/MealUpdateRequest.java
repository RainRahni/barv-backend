package com.barv.model;

import com.barv.model.Meal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealUpdateRequest {
    private Meal newMeal;
    private Meal currentMeal;
}
