package com.example.ratatouille.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ratatouille.model.MealDto;

import java.util.List;

@Dao
public interface MealDAO {
    @Query("SELECT * From Meal")
    LiveData<List<MealDto>> getAllMeal();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal (MealDto meal);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllMeal (List<MealDto> meal);
    @Query("DELETE FROM Meal")
    public void deleteAllMeals();
    @Delete
    void deleteMeal (MealDto meal);
    @Query("UPDATE Meal SET day = :day WHERE idMeal = :meal_id")
    void updateColumnDay(String meal_id, String day);
    @Query("SELECT * From Meal WHERE day = :day")
    LiveData<List<MealDto>> getMealByDay(String day);
    @Query("UPDATE Meal set day = null WHERE idMeal = :meal_id")
    void deleteDay(String meal_id);

}
