package com.example.ratatouille.Meal.presenter;


import com.example.ratatouille.Meal.view.MealViewInterface;
import com.example.ratatouille.Meal.view.ViewMealInterface;
import com.example.ratatouille.Network.NetworkDelegate;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

public class MealPresenter implements NetworkDelegate,MealViewInterface {
    ViewMealInterface view;
    Repository repo;
    public MealPresenter(ViewMealInterface view,Repository repo) {
        this.view = view;
        this.repo=repo;
    }



    @Override
    public void getMealByName(String search) {
        repo.getMealByName(this,search);

    }

    @Override
    public void onSuccessResult(MealDto[] mealDto) {
        view.getSearchResult(mealDto);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }
}
