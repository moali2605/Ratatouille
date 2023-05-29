package com.example.ratatouille.FavouriteMeal.view;

import com.example.ratatouille.model.MealDto;

public interface DeleteInterface {
    public void onClick(MealDto meal);
    public void onClickShowMeal(MealDto meal);
    public void onClickAddToDaily(String mealId);
}
