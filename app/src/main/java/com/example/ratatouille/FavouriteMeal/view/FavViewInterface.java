package com.example.ratatouille.FavouriteMeal.view;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.model.MealDto;

import java.util.List;

public interface FavViewInterface {
    public void getFavMeal(LiveData<List<MealDto>> meal);
}
