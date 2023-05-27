package com.example.ratatouille.home.view;

import com.example.ratatouille.model.MealDto;

public interface ViewInterface {
    public void getMeal(MealDto[] meal);
    public void getIngredient(MealDto[] meal);
    public void getCategories(MealDto[] meal);
    public void getCountry(MealDto[] meal);


}
