package com.example.ratatouille.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ratatouille.Network.MealClient;
import com.example.ratatouille.R;
import com.example.ratatouille.db.ConcreteLocalSource;
import com.example.ratatouille.home.presenter.HomePresenter;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;


public class HomeFragment extends Fragment implements ViewInterface {

    HomePresenter homePresenter;
    TextView tvDIName;
    ImageView ivDailyInspiration;
    Button btnDI;
    CheckBox cbDI;
    public static String TAG="MealApp";
    MealDto [] meals;


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
        homePresenter=new HomePresenter(this, Repository.getInstance(MealClient.getInstance(),ConcreteLocalSource.getInstance(getContext()),getContext()));
        homePresenter.getRandomMeal();

        tvDIName=v.findViewById(R.id.tvDIName);

    }
    @Override
    public void getMeal(MealDto[] meal) {
        tvDIName.setText(meal[0].getStrMeal());
    }

}