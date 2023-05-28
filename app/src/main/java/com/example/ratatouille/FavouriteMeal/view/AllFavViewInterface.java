package com.example.ratatouille.FavouriteMeal.view;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.model.MealDto;

import java.util.List;

public interface AllFavViewInterface {
    public LiveData<List<MealDto>> getFavMeals();
    public void deleteFromFav(MealDto meal);
}
