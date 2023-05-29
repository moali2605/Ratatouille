package com.example.ratatouille.FavouriteMeal.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ratatouille.FavouriteMeal.presenter.FavouritePresenter;
import com.example.ratatouille.FavouriteMeal.view.FavouriteMealFragmentDirections;
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
    Button btnSaturdayDi, btnSundayDi, btnMondayDi, btnTuesdayDi, btnWednesdayDi, btnThursdayDi, btnFridayDi;
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

        btnSaturdayDi = v.findViewById(R.id.btnSaturdayDi);
        btnSundayDi = v.findViewById(R.id.btnSundayDi);
        btnMondayDi = v.findViewById(R.id.btnMondayDi);
        btnTuesdayDi = v.findViewById(R.id.btnTuesdayDi);
        btnWednesdayDi = v.findViewById(R.id.btnWednesdayDi);
        btnThursdayDi = v.findViewById(R.id.btnThursdayDi);
        btnFridayDi = v.findViewById(R.id.btnFridayDi);

        recyclerView = v.findViewById(R.id.rvFav);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        favAdapter = new FavAdapter(getContext(), this);
        recyclerView.setAdapter(favAdapter);
        favouritePresenter = new FavouritePresenter(Repository.getInstance(MealClient.getInstance(), ConcreteLocalSource.getInstance(getContext()), getContext()), this);
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
    public void onClickShowMeal(MealDto meal) {
        com.example.ratatouille.FavouriteMeal.view.FavouriteMealFragmentDirections.ActionFavouriteMealFragmentToMealFragment action = FavouriteMealFragmentDirections.actionFavouriteMealFragmentToMealFragment(meal);
        Navigation.findNavController(getView()).navigate(action);

    }

    @Override
    public void onClickAddToDaily(String mealId) {
        showDialog(mealId);

    }

    private void showDialog(String mealId) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.pick_day_dialog);

        Button btnSaturday = dialog.findViewById(R.id.btnSaturdayDi);
        Button btnSunday = dialog.findViewById(R.id.btnSundayDi);
        Button btnMonday = dialog.findViewById(R.id.btnMondayDi);
        Button btnTuesday = dialog.findViewById(R.id.btnTuesdayDi);
        Button btnWednesday = dialog.findViewById(R.id.btnWednesdayDi);
        Button btnThursday = dialog.findViewById(R.id.btnThursdayDi);
        Button btnFriday = dialog.findViewById(R.id.btnFridayDi);

        btnSaturday.setOnClickListener(v -> {
            favouritePresenter.addToDaily("Saturday", mealId);
            dialog.dismiss();
        });

        btnSunday.setOnClickListener(v -> {
            favouritePresenter.addToDaily("Sunday", mealId);
            dialog.dismiss();
        });

        btnMonday.setOnClickListener(v -> {
            favouritePresenter.addToDaily("Monday", mealId);
            dialog.dismiss();
        });

        btnTuesday.setOnClickListener(v -> {
            favouritePresenter.addToDaily("Tuesday", mealId);
            dialog.dismiss();
        });

        btnWednesday.setOnClickListener(v -> {
            favouritePresenter.addToDaily("Wednesday", mealId);
            dialog.dismiss();
        });

        btnThursday.setOnClickListener(v -> {
            favouritePresenter.addToDaily("Thursday", mealId);
            dialog.dismiss();
        });

        btnFriday.setOnClickListener(v -> {
            favouritePresenter.addToDaily("Friday", mealId);
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void getFavMeal(LiveData<List<MealDto>> meal) {

    }


}