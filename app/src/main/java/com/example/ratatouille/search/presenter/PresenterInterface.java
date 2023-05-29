package com.example.ratatouille.search.presenter;

import com.example.ratatouille.model.MealDto;

public interface PresenterInterface {
    public void getMealByName(String search);
    public void insetMealToFav(MealDto meal);
}
