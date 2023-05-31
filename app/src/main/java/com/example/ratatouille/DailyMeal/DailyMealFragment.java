package com.example.ratatouille.DailyMeal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.example.ratatouille.DailyMeal.view.DailyMealFragmentDirections;
import com.example.ratatouille.Activity.HomeActivity;
import com.example.ratatouille.R;


public class DailyMealFragment extends Fragment {

    Button btnSaturdayDi, btnSundayDi, btnMondayDi, btnThursdayDi, btnWednesdayDi, btnTuesdayDi, btnFridayDi;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Override
    public void onStart() {
        super.onStart();
        ((HomeActivity) requireActivity()).bottomNavigationBar.setVisibility(View.VISIBLE);
    }

    public DailyMealFragment() {
        // Required empty public constructor
    }

    public static DailyMealFragment newInstance(String param1, String param2) {
        DailyMealFragment fragment = new DailyMealFragment();
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
        return inflater.inflate(R.layout.fragment_daily_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSaturdayDi = view.findViewById(R.id.btnSaturdayDi);
        btnSundayDi = view.findViewById(R.id.btnSundayDi);
        btnMondayDi = view.findViewById(R.id.btnMondayDi);
        btnThursdayDi = view.findViewById(R.id.btnThursdayDi);
        btnWednesdayDi = view.findViewById(R.id.btnWednesdayDi);
        btnTuesdayDi = view.findViewById(R.id.btnTuesdayDi);
        btnFridayDi = view.findViewById(R.id.btnFridayDi);
        btnSaturdayDi.setOnClickListener(v -> {
            DailyMealFragmentDirections.ActionDailyMealFragmentToShowDailyMealFragment action = DailyMealFragmentDirections.actionDailyMealFragmentToShowDailyMealFragment("Saturday");
            Navigation.findNavController(getView()).navigate(action);
        });

        btnSundayDi.setOnClickListener(v -> {
            DailyMealFragmentDirections.ActionDailyMealFragmentToShowDailyMealFragment action = DailyMealFragmentDirections.actionDailyMealFragmentToShowDailyMealFragment("Sunday");
            Navigation.findNavController(getView()).navigate(action);
        });

        btnMondayDi.setOnClickListener(v -> {
            DailyMealFragmentDirections.ActionDailyMealFragmentToShowDailyMealFragment action = DailyMealFragmentDirections.actionDailyMealFragmentToShowDailyMealFragment("Monday");
            Navigation.findNavController(getView()).navigate(action);
        });

        btnThursdayDi.setOnClickListener(v -> {
            DailyMealFragmentDirections.ActionDailyMealFragmentToShowDailyMealFragment action = DailyMealFragmentDirections.actionDailyMealFragmentToShowDailyMealFragment("Thursday");
            Navigation.findNavController(getView()).navigate(action);
        });

        btnWednesdayDi.setOnClickListener(v -> {
            DailyMealFragmentDirections.ActionDailyMealFragmentToShowDailyMealFragment action = DailyMealFragmentDirections.actionDailyMealFragmentToShowDailyMealFragment("Wednesday");
            Navigation.findNavController(getView()).navigate(action);
        });

        btnTuesdayDi.setOnClickListener(v -> {
            DailyMealFragmentDirections.ActionDailyMealFragmentToShowDailyMealFragment action = DailyMealFragmentDirections.actionDailyMealFragmentToShowDailyMealFragment("Tuesday");
            Navigation.findNavController(getView()).navigate(action);
        });

        btnFridayDi.setOnClickListener(v -> {
            DailyMealFragmentDirections.ActionDailyMealFragmentToShowDailyMealFragment action = DailyMealFragmentDirections.actionDailyMealFragmentToShowDailyMealFragment("Friday");
            Navigation.findNavController(getView()).navigate(action);
        });
    }
}