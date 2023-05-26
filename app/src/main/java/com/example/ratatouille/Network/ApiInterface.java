package com.example.ratatouille.Network;

import com.example.ratatouille.model.ArrayMealDto;
import com.example.ratatouille.model.MealDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("filter.php?i={ingredient}")
    Call<ArrayMealDto> getMealsByIngredient(@Path("ingredient")String ingredient);
    @GET("filter.php?c={categories}")
    Call<ArrayMealDto> getMealsByCategories(@Path("categories")String categories);
    @GET("filter.php?a={country}")
    Call<ArrayMealDto> getMealsByCountry(@Path("categories")String country);
    @GET("lookup.php?i={id}")
    Call<ArrayMealDto> getMealsById(@Path("id")String id);
    @GET("search.php?s={name}")
    Call<ArrayMealDto> getMealsByName(@Path("name")String name);
    @GET("random.php")
    Call<ArrayMealDto> getRandomMeal();
    @GET("list.php?a=list")
    Call<ArrayMealDto> getCountry();
    @GET("list.php?i=list")
    Call<ArrayMealDto> getIngredient();
    @GET("list.php?c=list")
    Call<ArrayMealDto> getCategory();
}
