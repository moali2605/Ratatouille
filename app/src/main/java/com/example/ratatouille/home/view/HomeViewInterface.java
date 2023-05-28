package com.example.ratatouille.home.view;

import com.example.ratatouille.model.MealDto;

public interface HomeViewInterface {
    public void getRandomMeal();
    public void getIngredient();
    public void getCategories();
    public void getCountry();
    public void addToFav(MealDto meal);
}
