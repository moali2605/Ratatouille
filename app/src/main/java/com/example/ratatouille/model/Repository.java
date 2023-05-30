package com.example.ratatouille.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.ratatouille.Network.NetworkDelegate;
import com.example.ratatouille.Network.RemoteSource;
import com.example.ratatouille.db.LocalSource;

import java.util.List;

public class Repository implements RepositoryInterface {
    private Context context;
    RemoteSource remoteSource;
    LocalSource localSource;
    private static Repository repo=null;
    private Repository(RemoteSource remoteSource,LocalSource localSource,Context context){
        this.context=context;
        this.remoteSource=remoteSource;
        this.localSource=localSource;
    }
    public static Repository getInstance(RemoteSource remoteSource,LocalSource localSource,Context context){
        if (repo==null){
            repo=new Repository(remoteSource,localSource,context);
        }
        return repo;
    }

    @Override
    public void getMealByIngredient(NetworkDelegate networkDelegate, String search) {
        remoteSource.enqueueCallIngredient(networkDelegate,search);
    }

    @Override
    public void getMealByCategories(NetworkDelegate networkDelegate, String search) {
        remoteSource.enqueueCallCategories(networkDelegate,search);
    }

    @Override
    public void getMealByCountry(NetworkDelegate networkDelegate, String search) {
        remoteSource.enqueueCallCountry(networkDelegate,search);
    }

    @Override
    public void getMealById(NetworkDelegate networkDelegate, int search) {
        remoteSource.enqueueCallId(networkDelegate,search);
    }

    @Override
    public void getMealByName(NetworkDelegate networkDelegate, String search) {
        remoteSource.enqueueCallName(networkDelegate,search);
    }

    @Override
    public void getMealRandom(NetworkDelegate networkDelegate) {
        remoteSource.enqueueRandomMeal(networkDelegate);
    }

    @Override
    public void getIngredient(NetworkDelegate networkDelegate) {
        remoteSource.enqueueGetIngredient(networkDelegate);
    }

    @Override
    public void getCategory(NetworkDelegate networkDelegate) {
        remoteSource.enqueueGetCategory(networkDelegate);
    }

    @Override
    public void getCountry(NetworkDelegate networkDelegate) {
        remoteSource.enqueueGetCountry(networkDelegate);
    }

    @Override
    public void insertMeal(MealDto meal) {
        localSource.insertMeal(meal);
    }

    @Override
    public void deleteMeal(MealDto meal) {
        localSource.deleteMeal(meal);
    }

    @Override
    public LiveData<List<MealDto>> getStoredMeal() {
        return localSource.getMeal();
    }

    @Override
    public void updateColumByDay(String mealId, String day) {
        localSource.updateDay(mealId,day);
    }

    @Override
    public LiveData<List<MealDto>> getMealByDay(String day) {
        return localSource.getMealByDay(day);
    }

    @Override
    public void deleteDay(String mealId) {
        localSource.deleteDay(mealId);
    }

    @Override
    public void insertAllMeal(List<MealDto> meal) {
        localSource.insertAllMeal(meal);
    }

    @Override
    public void deleteAllMeals() {
        localSource.deleteAllMeals();
    }
}
