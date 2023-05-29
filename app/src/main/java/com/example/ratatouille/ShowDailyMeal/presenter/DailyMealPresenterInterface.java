package com.example.ratatouille.ShowDailyMeal.presenter;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.model.MealDto;

import java.util.List;

public interface DailyMealPresenterInterface {
    public LiveData<List<MealDto>> searchByDay(String day);
    public void deleteFromDaily(String mealId);

}
