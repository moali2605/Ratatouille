package com.example.ratatouille.ShowDailyMeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratatouille.FavouriteMeal.view.FavAdapter;
import com.example.ratatouille.Ingredient.view.ShowListOfMealFragmentDirections;
import com.example.ratatouille.Network.MealClient;
import com.example.ratatouille.R;
import com.example.ratatouille.ShowDailyMeal.presenter.DailyMealPresenter;
import com.example.ratatouille.ShowDailyMeal.presenter.DailyMealPresenterInterface;
import com.example.ratatouille.ShowDailyMeal.view.ShowDailyMealFragmentDirections;
import com.example.ratatouille.db.ConcreteLocalSource;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class ShowDailyMealFragment extends Fragment implements DailyMealInterface ,DailyMealOnClick {
    RecyclerView recyclerView;

    DailyMealPresenter dailyMealPresenter;
    DailyMealAdapter dailyMealAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ShowDailyMealFragment() {
        // Required empty public constructor
    }

    public static ShowDailyMealFragment newInstance(String param1, String param2) {
        ShowDailyMealFragment fragment = new ShowDailyMealFragment();
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
        return inflater.inflate(R.layout.fragment_show_daily_meal, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String day=ShowDailyMealFragmentArgs.fromBundle(getArguments()).getDay();
        recyclerView = view.findViewById(R.id.rvListOf_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        dailyMealAdapter = new DailyMealAdapter(getContext(),this);
        recyclerView.setAdapter(dailyMealAdapter);
        dailyMealPresenter=new DailyMealPresenter( Repository.getInstance(MealClient.getInstance(), ConcreteLocalSource.getInstance(getContext()), getContext()),this);
        dailyMealPresenter.searchByDay(day).observe(getViewLifecycleOwner(), meal -> {
            dailyMealAdapter.setList((ArrayList<MealDto>) meal);
            dailyMealAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onClickShowMeal(MealDto meal) {
        com.example.ratatouille.ShowDailyMeal.view.ShowDailyMealFragmentDirections.ActionShowDailyMealFragmentToMealFragment action=ShowDailyMealFragmentDirections.actionShowDailyMealFragmentToMealFragment(meal);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void onClickDeleteDay(String mealId) {
        dailyMealPresenter.deleteFromDaily(mealId);
    }
}