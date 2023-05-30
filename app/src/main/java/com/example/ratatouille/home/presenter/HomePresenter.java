package com.example.ratatouille.home.presenter;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.ratatouille.Network.NetworkDelegate;
import com.example.ratatouille.home.view.HomeFragment;
import com.example.ratatouille.home.view.HomeViewInterface;
import com.example.ratatouille.home.view.ViewInterface;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

import java.util.List;

public class HomePresenter implements NetworkDelegate, HomeViewInterface {
    private Repository repo;
    private ViewInterface view;

    public HomePresenter(ViewInterface view, Repository repo) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void onSuccessResult(MealDto[] mealDto) {
        if(!mealDto[0].getStrMeal().equals("")) {
            view.getMeal(mealDto);
        }else if(!mealDto[0].getStrIngredient().equals("")){
            view.getIngredient(mealDto);
        }else if(!mealDto[0].getStrCategory().equals("")){
            view.getCategories(mealDto);
        }else if(!mealDto[0].getStrArea().equals("")){
            view.getCountry(mealDto);
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

    @Override
    public void addToFav(MealDto meal) {
        repo.insertMeal(meal);
    }

    @Override
    public void insertAllMeal(List<MealDto> meal) {
        repo.insertAllMeal(meal);
    }
}