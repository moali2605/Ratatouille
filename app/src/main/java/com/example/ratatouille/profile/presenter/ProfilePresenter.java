package com.example.ratatouille.profile.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;
import com.example.ratatouille.profile.view.ProfileInterface;

import java.util.List;

public class ProfilePresenter implements ProfilePresenterInterface{

    Repository repo;
    ProfileInterface view;

    public ProfilePresenter(Repository repo, ProfileInterface view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void deleteAllMeals() {
        repo.deleteAllMeals();
    }

    @Override
    public void getFavMeals() {
         repo.getStoredMeal().observe((LifecycleOwner) view, new Observer<List<MealDto>>() {
             @Override
             public void onChanged(List<MealDto> mealDtos) {
                 view.onGetFavouriteMeal(mealDtos);
             }
         });

    }

}
