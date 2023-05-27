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

    public HomePresenter(ViewInterface view, Repository repo) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void onSuccessResult(MealDto[] mealDto) {
        if(!mealDto[0].getStrIngredient().equals("")){
            view.getIngredient(mealDto);
        }else if(!mealDto[0].getStrCategory().equals("")){
            view.getCategories(mealDto);
        }else if(!mealDto[0].getStrArea().equals("")){
            view.getCountry(mealDto);
        }else {
            view.getMeal(mealDto);
        }
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }

    @Override
    public void getRandomMeal() {
        repo.getMealRandom(this);

    }

    @Override
    public void getIngredient() {
        repo.getIngredient(this);
    }

    @Override
    public void getCategories() {
        repo.getCategory(this);
    }

    @Override
    public void getCountry() {
        repo.getCountry(this);
    }
}
