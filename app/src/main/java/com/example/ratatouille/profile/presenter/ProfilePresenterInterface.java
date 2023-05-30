package com.example.ratatouille.profile.presenter;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.model.MealDto;

import java.security.PublicKey;
import java.util.List;

public interface ProfilePresenterInterface {
    public void deleteAllMeals();
    public void getFavMeals();
}
