package com.example.ratatouille.Ingredient.presenter;

import com.example.ratatouille.Ingredient.view.ListOfMealViewInterface;
import com.example.ratatouille.Ingredient.view.ViewListOfMealInterface;
import com.example.ratatouille.Network.NetworkDelegate;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

public class ListOfMealPresenter implements NetworkDelegate, ListOfMealViewInterface {
    private Repository repo;
    private ViewListOfMealInterface view;

    public ListOfMealPresenter(ViewListOfMealInterface view, Repository repo) {
        this.repo = repo;
        this.view = view;
    }
    @Override
    public void onSuccessResult(MealDto[] mealDto) {
        view.getMealByIngredient(mealDto);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }
    @Override
    public void getMealByIngredient(String search) {
        repo.getMealByIngredient(this,search);
    }
}