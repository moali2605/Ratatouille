package com.example.ratatouille.Ingredient.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratatouille.Activity.HomeActivity;
import com.example.ratatouille.Ingredient.presenter.ListOfMealPresenter;
import com.example.ratatouille.Ingredient.view.ShowListOfMealFragmentDirections;
import com.example.ratatouille.Network.MealClient;
import com.example.ratatouille.R;
import com.example.ratatouille.db.ConcreteLocalSource;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;

import java.util.Arrays;

public class ShowListOfMealFragment extends Fragment implements ViewListOfMealInterface,OnClickShowMealInterface {
    ListOfMealViewInterface listOfMealViewInterface;
    RecyclerView recyclerView;
    ShowListOfMealAdapter showListOfMealAdapter;
    ListOfMealPresenter listOfMealPresenter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    @Override
    public void onStart() {
        super.onStart();
        ((HomeActivity) requireActivity()).bottomNavigationBar.setVisibility(View.GONE);
    }

    public ShowListOfMealFragment() {
        // Required empty public constructor
    }

    public ShowListOfMealFragment(Context context, ListOfMealViewInterface listOfMealViewInterface) {
        this.listOfMealViewInterface = listOfMealViewInterface;
    }


    public static ShowListOfMealFragment newInstance(String param1, String param2) {
        ShowListOfMealFragment fragment = new ShowListOfMealFragment();
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
        return inflater.inflate(R.layout.fragment_show_list_of_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        showListOfMealAdapter = new ShowListOfMealAdapter(getContext(),this);
        listOfMealPresenter = new ListOfMealPresenter(this, Repository.getInstance(MealClient.getInstance(), ConcreteLocalSource.getInstance(getContext()), getContext()));
        listOfMealViewInterface = new ListOfMealPresenter(this, Repository.getInstance(MealClient.getInstance(), ConcreteLocalSource.getInstance(getContext()), getContext()));
        String search = ShowListOfMealFragmentArgs.fromBundle(getArguments()).getName();
        String type = ShowListOfMealFragmentArgs.fromBundle(getArguments()).getType();

        recyclerView = v.findViewById(R.id.rvListOf_item);
        LinearLayoutManager linearLayoutManagerCountry = new LinearLayoutManager(getContext());
        linearLayoutManagerCountry.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagerCountry);
        recyclerView.setAdapter(showListOfMealAdapter);
        if (type.equals("ingredient")) {
            listOfMealViewInterface.getMealByIngredient(search);
        } else if (type.equals("categories")) {
            listOfMealViewInterface.getMealByCategory(search);
        } else if (type.equals("country")) {
            listOfMealViewInterface.getMealByCountry(search);
        }
    }


    @Override
    public void getSearchResult(MealDto[] meal) {
        showListOfMealAdapter.setList(Arrays.asList(meal));
        showListOfMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(MealDto meal) {
        com.example.ratatouille.Ingredient.view.ShowListOfMealFragmentDirections.ActionShowListOfMealFragmentToMealFragment action=ShowListOfMealFragmentDirections.actionShowListOfMealFragmentToMealFragment(meal);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void onClickFav(MealDto meal) {
        listOfMealPresenter.addToFav(meal);
    }
}