package com.example.ratatouille.Network;

import android.util.Log;

import com.example.ratatouille.model.ArrayMealDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClient implements RemoteSource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "Client";

    private static MealClient client = null;
    private  static ApiInterface apiInterFace;

    private MealClient() {
    }

    public static MealClient getInstance() {
        if (client == null) {
            client = new MealClient();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client2 = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterFace = retrofit.create(ApiInterface.class);
        }
        return client;
    }






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
    public void enqueueCallId(NetworkDelegate networkDelegate, int search) {
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
        Log.i(TAG, "enqueueRandomMeal: getRandom meal in network");
        callRandomMeal.enqueue(new Callback<ArrayMealDto>() {
            @Override
            public void onResponse(Call<ArrayMealDto> call, Response<ArrayMealDto> response) {
                networkDelegate.onSuccessResult(response.body().getMeal());
                Log.i(TAG, "enqueueRandomMeal: getRandom meal in network onResponse");

            }

            @Override
            public void onFailure(Call<ArrayMealDto> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
                Log.i(TAG, "enqueueRandomMeal: getRandom meal in network onFailure");
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
