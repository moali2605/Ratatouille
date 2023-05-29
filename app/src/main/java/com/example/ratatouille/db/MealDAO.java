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
    @Query("UPDATE Meal SET day = :day WHERE idMeal = :meal_id")
    void updateColumnDay(String meal_id, String day);
    @Query("SELECT * From Meal WHERE day = :day")
    LiveData<List<MealDto>> getMealByDay(String day);
    @Query("UPDATE Meal set day = null WHERE idMeal = :meal_id")
    void deleteDay(String meal_id);
}
