package com.example.ratatouille.Network;

import com.example.ratatouille.model.ArrayMealDto;
import com.example.ratatouille.model.MealDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("filter.php")
    Call<ArrayMealDto> getMealsByIngredient(@Query("i") String ingredient);
    @GET("filter.php")
    Call<ArrayMealDto> getMealsByCategories(@Query("c") String category);
    @GET("filter.php")
    Call<ArrayMealDto> getMealsByCountry(@Query("a") String area);
    @GET("lookup.php")
    Call<ArrayMealDto> getMealsById(@Query("i") int id);
    @GET("search.php")
    Call<ArrayMealDto> getMealsByName(@Query("s")String name);
    @GET("random.php")
    Call<ArrayMealDto> getRandomMeal();
    @GET("list.php?a=list")
    Call<ArrayMealDto> getCountry();
    @GET("list.php?i=list")
    Call<ArrayMealDto> getIngredient();
    @GET("list.php?c=list")
    Call<ArrayMealDto> getCategory();
}
