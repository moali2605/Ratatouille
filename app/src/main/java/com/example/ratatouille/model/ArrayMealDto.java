package com.example.ratatouille.model;

public class ArrayMealDto {
    MealDto[] meals;

    public MealDto [] getMeal(){
        return meals;
    }

    public ArrayMealDto(MealDto[] meals) {
        this.meals = meals;
    }

    public MealDto[] getMeals() {
        return meals;
    }

    public void setMeals(MealDto[] meals) {
        this.meals = meals;
    }
}
