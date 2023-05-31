package com.example.ratatouille.Activity.Home.presenter;

import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

import java.util.List;

public class FirebasePresenter implements FirebasePresenterInterface {

    Repository repo;

    public FirebasePresenter(Repository repo) {
        this.repo = repo;
    }

    @Override
    public void insertAllMeal(List<MealDto> meal) {
        repo.insertAllMeal(meal);

    }
}
