package com.example.ratatouille.Meal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouille.Meal.MealFragmentArgs;
import com.example.ratatouille.Meal.presenter.MealPresenter;
import com.example.ratatouille.R;
import com.example.ratatouille.model.MealDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MealFragment extends Fragment implements ViewMealInterface {
    MealDto meal;
    ImageView ivMeal;
    TextView tvMeal, tvCountry, tvCatogry, tvDescription;

    MealIngredientAdapter ingredientAdapter;
    MealPresenter mealPresenter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MealFragment() {
        // Required empty public constructor
    }

    public static MealFragment newInstance(String param1, String param2) {
        MealFragment fragment = new MealFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        mealPresenter=new MealPresenter(this);
        ingredientAdapter=new MealIngredientAdapter(getContext());
        meal = MealFragmentArgs.fromBundle(getArguments()).getMeal();
        ivMeal = v.findViewById(R.id.ivMeal);
        tvMeal = v.findViewById(R.id.tvMeal);
        tvCatogry = v.findViewById(R.id.tvCatogry);
        tvCountry = v.findViewById(R.id.tvCountry);
        tvDescription = v.findViewById(R.id.tvDescription);
        Glide.with(getContext()).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(400, 250))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(ivMeal);
        tvMeal.setText(meal.getStrMeal());
        tvCountry.setText(meal.getStrArea());
        tvCatogry.setText(meal.getStrCategory());
        tvDescription.setText(meal.getStrInstructions());
        mealPresenter.getMeal(meal);
    }


    @Override
    public void getMeal(MealDto meal) {
        ingredientAdapter.setList(Arrays.asList(meal));
        ingredientAdapter.notifyDataSetChanged();
    }
}