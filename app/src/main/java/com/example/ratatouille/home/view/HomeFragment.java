package com.example.ratatouille.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouille.Network.MealClient;
import com.example.ratatouille.R;
import com.example.ratatouille.db.ConcreteLocalSource;
import com.example.ratatouille.home.presenter.HomePresenter;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;
import com.squareup.picasso.Picasso;

import java.util.Arrays;


public class HomeFragment extends Fragment implements ViewInterface {

    HomePresenter homePresenter;
    TextView tvDIName;
    ImageView ivDailyInspiration;
    Button btnDI;
    CheckBox cbDI;
    public static String TAG="MealApp";
    MealDto [] meals;
    IngredientAdapter ingredientAdapter;
    RecyclerView recyclerViewIngredient,recyclerViewCategories,recyclerViewCountry;
    CategoriesAdapter categoriesAdapter;

    
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        tvDIName=v.findViewById(R.id.tvDIName);
        ivDailyInspiration=v.findViewById(R.id.ivDailyInspiration);

        //homePresenter
        homePresenter=new HomePresenter(this, Repository.getInstance(MealClient.getInstance(),ConcreteLocalSource.getInstance(getContext()),getContext()));
        homePresenter.getRandomMeal();
        homePresenter.getIngredient();
        homePresenter.getCategories();

        //recyclerViewIngredient
        recyclerViewIngredient=v.findViewById(R.id.rvMbyIngredient);
        LinearLayoutManager linearLayoutManagerIngredient = new LinearLayoutManager(getContext());
        linearLayoutManagerIngredient.setOrientation(recyclerViewIngredient.HORIZONTAL);
        recyclerViewIngredient.setLayoutManager(linearLayoutManagerIngredient);
        ingredientAdapter=new IngredientAdapter(getContext());
        recyclerViewIngredient.setAdapter(ingredientAdapter);

        //recyclerViewCategories
        recyclerViewCategories=v.findViewById(R.id.rvCategories);
        LinearLayoutManager linearLayoutManagerCategories = new LinearLayoutManager(getContext());
        linearLayoutManagerCategories.setOrientation(recyclerViewCategories.HORIZONTAL);
        recyclerViewCategories.setLayoutManager(linearLayoutManagerCategories);
        categoriesAdapter=new CategoriesAdapter(getContext());
        recyclerViewCategories.setAdapter(categoriesAdapter);

        //recyclerViewCountry
        recyclerViewCountry=v.findViewById(R.id.rvCountry);
        LinearLayoutManager linearLayoutManagerCountry = new LinearLayoutManager(getContext());
        linearLayoutManagerCountry.setOrientation(recyclerViewCountry.HORIZONTAL);
        recyclerViewCountry.setLayoutManager(linearLayoutManagerCountry);


    }
    @Override
    public void getMeal(MealDto[] meal) {
        tvDIName.setText(meal[0].getStrMeal());
        Glide.with(getContext()).load(meal[0].getStrMealThumb())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(ivDailyInspiration);
    }
    @Override
    public void getIngredient(MealDto[] meal) {
        ingredientAdapter.setList(Arrays.asList(meal));
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategories(MealDto[] meal) {
        categoriesAdapter.setList(Arrays.asList(meal));
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCountry(MealDto[] meal) {

    }

}