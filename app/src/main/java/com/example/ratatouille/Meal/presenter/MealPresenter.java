package com.example.ratatouille.Meal.presenter;


import com.example.ratatouille.Meal.view.MealFragment;
import com.example.ratatouille.Meal.view.MealViewInterface;
import com.example.ratatouille.Meal.view.ViewMealInterface;
import com.example.ratatouille.home.view.ViewInterface;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

import java.util.ArrayList;
import java.util.List;

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
