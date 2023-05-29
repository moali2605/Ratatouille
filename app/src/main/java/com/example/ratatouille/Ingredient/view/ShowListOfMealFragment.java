package com.example.ratatouille.Ingredient.view;

import android.content.Context;
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

import com.example.ratatouille.R;
import com.example.ratatouille.model.MealDto;

import java.util.Arrays;

public class ShowListOfMealFragment extends Fragment implements ViewListOfMealInterface {
    ListOfMealViewInterface listOfMealViewInterface;
    RecyclerView recyclerView;
    ShowListOfMealAdapter showListOfMealAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ShowListOfMealFragment() {
        // Required empty public constructor
    }
    public ShowListOfMealFragment(Context context,ListOfMealViewInterface listOfMealViewInterface){
        this.listOfMealViewInterface=listOfMealViewInterface;
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

        String search= ShowListOfMealFragmentArgs.fromBundle(getArguments()).toString();
        Log.i("zzzzzzzzz", "onViewCreated: "+search);
        listOfMealViewInterface.getMealByIngredient(search);

        recyclerView = v.findViewById(R.id.rvListOf_item);
        LinearLayoutManager linearLayoutManagerCountry = new LinearLayoutManager(getContext());
        linearLayoutManagerCountry.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagerCountry);
        showListOfMealAdapter = new ShowListOfMealAdapter(getContext());
        recyclerView.setAdapter(showListOfMealAdapter);
    }

    @Override
    public void getMealByIngredient(MealDto[] meal) {
        showListOfMealAdapter.setList(Arrays.asList(meal));
        showListOfMealAdapter.notifyDataSetChanged();
    }
}