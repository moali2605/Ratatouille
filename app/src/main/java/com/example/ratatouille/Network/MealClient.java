package com.example.ratatouille.Network;

import com.example.ratatouille.model.ArrayMealDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClient implements RemoteSource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "Client";

    private static MealClient client = null;

    private MealClient() {
    }

    public static MealClient getInstance() {
        if (client == null) {
            client = new MealClient();
        }
        return client;
    }

    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiInterface apiInterFace = retrofit.create(ApiInterface.class);


    @Override
    public void enqueueCallIngredient(NetworkDelegate networkDelegate, String search) {
        Call<ArrayMealDto> callIngredient = apiInterFace.getMealsByIngredient(search);
        callIngredient.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueCallCategories(NetworkDelegate networkDelegate, String search) {
        Call<ArrayMealDto> callCategories = apiInterFace.getMealsByCategories(search);
        callCategories.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueCallCountry(NetworkDelegate networkDelegate, String search) {
        Call<ArrayMealDto> callCountry = apiInterFace.getMealsByCountry(search);
        callCountry.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });

    }

    @Override
    public void enqueueCallId(NetworkDelegate networkDelegate, String search) {
        Call<ArrayMealDto> callId = apiInterFace.getMealsById(search);
        callId.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueCallName(NetworkDelegate networkDelegate, String search) {
        Call<ArrayMealDto> callName = apiInterFace.getMealsByName(search);
        callName.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueRandomMeal(NetworkDelegate networkDelegate) {
        Call<ArrayMealDto> callRandomMeal = apiInterFace.getRandomMeal();
        callRandomMeal.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueGetIngredient(NetworkDelegate networkDelegate) {
        Call<ArrayMealDto> callIngredient = apiInterFace.getIngredient();
        callIngredient.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueGetCategory(NetworkDelegate networkDelegate) {
        Call<ArrayMealDto> callCategory = apiInterFace.getCategory();
        callCategory.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueGetCountry(NetworkDelegate networkDelegate) {
        Call<ArrayMealDto> callCountry = apiInterFace.getCountry();
        callCountry.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }
}
