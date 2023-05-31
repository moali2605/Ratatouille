package com.example.ratatouille;


import com.example.ratatouille.MealViewInterface;
import com.example.ratatouille.Network.NetworkDelegate;
import com.example.ratatouille.ViewMealInterface;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

public class MealPresenter implements NetworkDelegate, MealViewInterface {
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
    public void addToFav(MealDto meal) {
        repo.insertMeal(meal);
    }

    @Override
    public void onSuccessResult(MealDto[] mealDto) {
        view.getSearchResult(mealDto);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }
}
