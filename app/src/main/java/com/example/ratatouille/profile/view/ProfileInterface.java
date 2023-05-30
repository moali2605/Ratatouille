package com.example.ratatouille.profile.view;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.model.MealDto;

import java.util.List;

public interface ProfileInterface {
    public void onGetFavouriteMeal(List<MealDto> mealDto);
}
