package com.example.ratatouille.Ingredient.view;

import com.example.ratatouille.model.MealDto;

public interface ListOfMealViewInterface {
    public void getMealByIngredient(String search);
    public void getMealByCategory(String search);
    public void getMealByCountry(String search);
    public void addToFav(MealDto meal);

}
