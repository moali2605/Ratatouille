package com.example.ratatouille.home.presenter;

import com.example.ratatouille.Network.NetworkDelegate;
import com.example.ratatouille.home.view.HomeFragment;
import com.example.ratatouille.home.view.HomeViewInterface;
import com.example.ratatouille.home.view.ViewInterface;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

public class HomePresenter implements NetworkDelegate, HomeViewInterface {
    private Repository repo;
    private ViewInterface view;
    public HomePresenter(ViewInterface view,Repository repo){
        this.repo=repo;
        this.view=view;
    }
    @Override
    public void onSuccessResult(MealDto[] mealDto) {
        view.getMeal(mealDto);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }

    @Override
    public void getRandomMeal() {
        repo.getMealRandom(this);
    }
}
