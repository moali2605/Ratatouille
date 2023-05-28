package com.example.ratatouille.Meal.presenter;


import com.example.ratatouille.Meal.view.MealViewInterface;
import com.example.ratatouille.Meal.view.ViewMealInterface;
import com.example.ratatouille.model.MealDto;

public class MealPresenter implements MealViewInterface {
    ViewMealInterface view;
    public MealPresenter(ViewMealInterface view) {
        this.view = view;
    }

    @Override
    public void getMeal(MealDto meal) {
        view.getMeal(meal);
    }
}
