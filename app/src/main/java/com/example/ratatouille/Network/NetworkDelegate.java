package com.example.ratatouille.Network;

import com.example.ratatouille.model.MealDto;

public interface NetworkDelegate {
    public void onSuccessResult(MealDto[] mealDto);
    public void onFailureResult(String errorMsg);
}
