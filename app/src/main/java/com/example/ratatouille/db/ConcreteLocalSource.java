package com.example.ratatouille.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.model.MealDto;

import java.util.List;

public class ConcreteLocalSource implements LocalSource{

    private MealDAO mealDAO;
    private LiveData<List<MealDto>> storeMeal;
    private static ConcreteLocalSource localSource=null;
    private ConcreteLocalSource(Context context){
        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO=db.productDAO();
        storeMeal=mealDAO.getAllMeal();
    }
    public static ConcreteLocalSource getInstance(Context context){
        if(localSource==null){
            localSource=new ConcreteLocalSource(context);
        }
        return localSource;
    }
    @Override
    public void insertMeal(MealDto meal) {
        new Thread(() -> {
            mealDAO.insertMeal(meal);
        }).start();
    }

    @Override
    public void deleteMeal(MealDto meal) {
        new Thread(() -> {
            mealDAO.deleteMeal(meal);
        }).start();
    }

    @Override
    public LiveData<List<MealDto>> getMeal() {
        return mealDAO.getAllMeal();
    }
}
