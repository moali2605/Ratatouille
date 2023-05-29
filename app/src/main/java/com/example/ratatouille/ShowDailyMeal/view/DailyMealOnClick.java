package com.example.ratatouille.ShowDailyMeal.view;

import com.example.ratatouille.model.MealDto;

public interface DailyMealOnClick {
    public void onClickShowMeal(MealDto meal);
    public void onClickDeleteDay(String mealId);

}
