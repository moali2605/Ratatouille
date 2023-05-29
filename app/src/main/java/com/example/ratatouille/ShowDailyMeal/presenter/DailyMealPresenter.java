package com.example.ratatouille.ShowDailyMeal.presenter;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.ShowDailyMeal.view.DailyMealInterface;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

import java.util.List;

public class DailyMealPresenter implements DailyMealPresenterInterface {
    Repository repo;
    DailyMealInterface view;

    public DailyMealPresenter(Repository repo, DailyMealInterface view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public LiveData<List<MealDto>> searchByDay(String day) {
        return repo.getMealByDay(day);
    }

    @Override
    public void deleteFromDaily(String mealId) {
        repo.deleteDay(mealId);
    }
}
