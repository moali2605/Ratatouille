package com.example.ratatouille.db;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.model.MealDto;

import java.util.List;

public interface LocalSource {
    public void insertMeal(MealDto meal);
    public  void deleteMeal(MealDto meal);
    public LiveData<List<MealDto>> getMeal();
}
