package com.example.ratatouille.search.presenter;

import com.example.ratatouille.Network.NetworkDelegate;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;
import com.example.ratatouille.search.view.SearchInterface;

public class SearchPresenter implements NetworkDelegate,PresenterInterface {
    Repository repo;
    SearchInterface view;

    public SearchPresenter(Repository repo, SearchInterface view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void onSuccessResult(MealDto[] mealDto) {
        view.getSearchResult(mealDto);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }

    @Override
    public void getMealByName(String search) {
        repo.getMealByName(this,search);
    }

    @Override
    public void insetMealToFav(MealDto meal) {
        repo.insertMeal(meal);
    }
}
