package com.example.ratatouille.home.view;

import com.example.ratatouille.model.MealDto;

import java.util.List;

public interface HomeViewInterface {
    public void getRandomMeal();
    public void getIngredient();
    public void getCategories();
    public void getCountry();
    public void addToFav(MealDto meal);
    public void insertAllMeal(List<MealDto> meal);

}