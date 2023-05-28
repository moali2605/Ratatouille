package com.example.ratatouille.FavouriteMeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratatouille.FavouriteMeal.presenter.FavouritePresenter;
import com.example.ratatouille.Network.MealClient;
import com.example.ratatouille.R;
import com.example.ratatouille.db.ConcreteLocalSource;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class FavouriteMealFragment extends Fragment implements DeleteInterface, FavViewInterface {

    RecyclerView recyclerView;
    FavAdapter favAdapter;
    FavouritePresenter favouritePresenter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public FavouriteMealFragment() {
        // Required empty public constructor
    }

    public static FavouriteMealFragment newInstance(String param1, String param2) {
        FavouriteMealFragment fragment = new FavouriteMealFragment();
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
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        recyclerView=v.findViewById(R.id.rvListOf_item);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        favAdapter=new FavAdapter(getContext(),this);
        recyclerView.setAdapter(favAdapter);
        favouritePresenter=new FavouritePresenter(Repository.getInstance(MealClient.getInstance(), ConcreteLocalSource.getInstance(getContext()),getContext() ), this);
        favouritePresenter.getFavMeals().observe(getViewLifecycleOwner(), new Observer<List<MealDto>>() {
            @Override
            public void onChanged(List<MealDto> meal) {
                favAdapter.setList((ArrayList<MealDto>) meal);
                favAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite_meal, container, false);
    }

    @Override
    public void onClick(MealDto meal) {
        favouritePresenter.deleteFromFav(meal);
    }

    @Override
    public void getFavMeal(LiveData<List<MealDto>> meal) {

    }
}