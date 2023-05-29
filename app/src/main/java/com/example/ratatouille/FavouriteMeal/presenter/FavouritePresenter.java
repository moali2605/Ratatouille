package com.example.ratatouille.FavouriteMeal.presenter;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.FavouriteMeal.view.AllFavViewInterface;
import com.example.ratatouille.FavouriteMeal.view.FavViewInterface;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

import java.util.List;

public class FavouritePresenter implements AllFavViewInterface {
    private Repository repo;
    private FavViewInterface view;
    public FavouritePresenter(Repository repo,FavViewInterface view){
        this.repo=repo;
        this.view=view;
    }
    @Override
    public LiveData<List<MealDto>> getFavMeals() {
        return repo.getStoredMeal();
    }

    @Override
    public void deleteFromFav(MealDto meal) {
        repo.deleteMeal(meal);
    }

    @Override
    public void addToDaily(String day,String mealId) {
        repo.updateColumByDay(day,mealId);
    }
}
