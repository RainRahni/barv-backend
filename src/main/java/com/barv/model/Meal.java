package com.barv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Meal {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "meal_sequence",
            sequenceName = "meal_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meal_sequence"
    )
    @EqualsAndHashCode.Exclude private Long mealId;
    private String name;
    private double calories;
    private double protein;
    private double carbohydrates;
    private double fats;
    @Enumerated(EnumType.STRING)
    private MealType type;
    @ManyToMany(mappedBy = "meals")
    private List<User> users;
    @OneToMany
    private List<Food> foods;
    public void addFoodsToList(List<Food> foodsToAdd) {
        for (Food food: foodsToAdd) {
            if (!foods.contains(food)) {
                foods.add(food);
            }
        }
    }
}
