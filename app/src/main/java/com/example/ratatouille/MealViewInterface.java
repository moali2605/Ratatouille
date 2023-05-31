package com.example.ratatouille;

import com.example.ratatouille.Network.NetworkDelegate;
import com.example.ratatouille.model.MealDto;

public interface MealViewInterface {

    public void getMealByName(String search);
    public void addToFav(MealDto meal);
}
