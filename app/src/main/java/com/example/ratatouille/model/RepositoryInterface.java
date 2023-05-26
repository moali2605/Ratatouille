package com.example.ratatouille.model;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.Network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {
    public void getMealByIngredient(NetworkDelegate networkDelegate, String search);

    public void getMealByCategories(NetworkDelegate networkDelegate, String search);

    public void getMealByCountry(NetworkDelegate networkDelegate, String search);
    public void getMealById(NetworkDelegate networkDelegate, String search);
    public void getMealByName(NetworkDelegate networkDelegate, String search);
    public void getMealRandom(NetworkDelegate networkDelegate);
    public void getIngredient(NetworkDelegate networkDelegate);
    public void getCategory(NetworkDelegate networkDelegate);
    public void getCountry(NetworkDelegate networkDelegate);

    public void insertMeal(MealDto meal);

    public void deleteMeal(MealDto meal);

    public LiveData<List<MealDto>> getStoredMeal();
}
