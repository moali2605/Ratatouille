package com.example.ratatouille.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDto {
    String email;
    List<MealDto> favMeal;

    public UserDto(String email, List<MealDto> favMeal) {
        this.email = email;
        this.favMeal = favMeal;
    }

    public UserDto(String email) {
        this.email = email;
    }

    public UserDto(Map<String, Object> data) {

        this.email = (String) data.get("email");

        List<Map<String, Object>> favMealsData = (List<Map<String, Object>>) data.get("favMeal");
        if (favMealsData != null) {
            this.favMeal = new ArrayList<>();
            for (Map<String, Object> mealData : favMealsData) {
                MealDto meal = new MealDto();
                meal.setIdMeal((String) mealData.get("idMeal"));
                meal.setStrMeal((String) mealData.get("strMeal"));
                meal.setStrDrinkAlternate((String) mealData.get("strDrinkAlternate"));
                meal.setStrCategory((String) mealData.get("strCategory"));
                meal.setStrArea((String) mealData.get("strArea"));
                meal.setStrInstructions((String) mealData.get("strInstructions"));
                meal.setStrMealThumb((String) mealData.get("strMealThumb"));
                meal.setStrTags((String) mealData.get("strTags"));
                meal.setStrYoutube((String) mealData.get("strYoutube"));
                meal.setStrIngredient1((String) mealData.get("strIngredient1"));
                meal.setStrIngredient2((String) mealData.get("strIngredient2"));
                meal.setStrIngredient3((String) mealData.get("strIngredient3"));
                meal.setStrIngredient4((String) mealData.get("strIngredient4"));
                meal.setStrIngredient5((String) mealData.get("strIngredient5"));
                meal.setStrIngredient6((String) mealData.get("strIngredient6"));
                meal.setStrIngredient7((String) mealData.get("strIngredient7"));
                meal.setStrIngredient8((String) mealData.get("strIngredient8"));
                meal.setStrIngredient9((String) mealData.get("strIngredient9"));
                meal.setStrIngredient10((String) mealData.get("strIngredient10"));
                meal.setStrIngredient11((String) mealData.get("strIngredient11"));
                meal.setStrIngredient12((String) mealData.get("strIngredient12"));
                meal.setStrIngredient13((String) mealData.get("strIngredient13"));
                meal.setStrIngredient14((String) mealData.get("strIngredient14"));
                meal.setStrIngredient15((String) mealData.get("strIngredient15"));
                meal.setStrIngredient16((String) mealData.get("strIngredient16"));
                meal.setStrIngredient17((String) mealData.get("strIngredient17"));
                meal.setStrIngredient18((String) mealData.get("strIngredient18"));
                meal.setStrIngredient19((String) mealData.get("strIngredient19"));
                meal.setStrIngredient20((String) mealData.get("strIngredient20"));
                meal.setStrMeasure1((String) mealData.get("strMeasure1"));
                meal.setStrMeasure2((String) mealData.get("strMeasure2"));
                meal.setStrMeasure3((String) mealData.get("strMeasure3"));
                meal.setStrMeasure4((String) mealData.get("strMeasure4"));
                meal.setStrMeasure5((String) mealData.get("strMeasure5"));
                meal.setStrMeasure6((String) mealData.get("strMeasure6"));
                meal.setStrMeasure7((String) mealData.get("strMeasure7"));
                meal.setStrMeasure8((String) mealData.get("strMeasure8"));
                meal.setStrMeasure9((String) mealData.get("strMeasure9"));
                meal.setStrMeasure10((String) mealData.get("strMeasure10"));
                meal.setStrMeasure11((String) mealData.get("strMeasure11"));
                meal.setStrMeasure12((String) mealData.get("strMeasure12"));
                meal.setStrMeasure13((String) mealData.get("strMeasure13"));
                meal.setStrMeasure14((String) mealData.get("strMeasure14"));
                meal.setStrMeasure15((String) mealData.get("strMeasure15"));
                meal.setStrMeasure16((String) mealData.get("strMeasure16"));
                meal.setStrMeasure17((String) mealData.get("strMeasure17"));
                meal.setStrMeasure18((String) mealData.get("strMeasure18"));
                meal.setStrMeasure19((String) mealData.get("strMeasure19"));
                meal.setStrMeasure20((String) mealData.get("strMeasure20"));
                meal.setDay((String) mealData.get("day"));
                this.favMeal.add(meal);
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MealDto> getFavMeal() {
        return favMeal;
    }

    public void setFavMeal(List<MealDto> favMeal) {
        this.favMeal = favMeal;
    }
}
