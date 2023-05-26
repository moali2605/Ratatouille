package com.example.ratatouille.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ratatouille.model.MealDto;

@Database(entities = {MealDto.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;
    public abstract MealDAO productDAO();
    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,
                            "MealDB")
                    .build();
        }
        return instance;
    }
}
