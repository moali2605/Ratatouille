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
    public void getMealById(NetworkDelegate networkDelegate, String search) {
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
}
