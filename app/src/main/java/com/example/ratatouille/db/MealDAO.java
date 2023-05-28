package com.example.ratatouille.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ratatouille.model.MealDto;

import java.util.List;

@Dao
public interface MealDAO {
    @Query("SELECT * From Meal")
    LiveData<List<MealDto>> getAllMeal();
    @Insert
    void insertMeal (MealDto meal);
    @Delete
    void deleteMeal (MealDto meal);
    //@Query("INSERT INTO MEAL")
}
