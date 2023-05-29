package com.example.ratatouille.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ratatouille.Network.MealClient;
import com.example.ratatouille.R;
import com.example.ratatouille.db.ConcreteLocalSource;
import com.example.ratatouille.home.view.IngredientAdapter;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;
import com.example.ratatouille.search.presenter.PresenterInterface;
import com.example.ratatouille.search.presenter.SearchPresenter;
import com.example.ratatouille.search.view.SearchFragmentDirections;

import java.util.Arrays;

public class SearchFragment extends Fragment implements SearchInterface,SearchInsertInterface {
    EditText etSearch;
    RecyclerView recyclerView;
    PresenterInterface presenterInterface;
    SearchAdapter searchAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        presenterInterface = new SearchPresenter(Repository.getInstance(MealClient.getInstance(), ConcreteLocalSource.getInstance(getContext()), getContext()), this);
        etSearch = v.findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = s.toString();
                presenterInterface.getMealByName(search);
            }
        });

        recyclerView = v.findViewById(R.id.rvSearch);
        LinearLayoutManager linearLayoutManagerIngredient = new LinearLayoutManager(getContext());
        linearLayoutManagerIngredient.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagerIngredient);
        searchAdapter = new SearchAdapter(getContext(),this);
        recyclerView.setAdapter(searchAdapter);

    }

    @Override
    public void getSearchResult(MealDto[] meal) {
        searchAdapter.setList(Arrays.asList(meal));
    }

    @Override
    public void onClick(MealDto meal) {
        presenterInterface.insetMealToFav(meal);
    }

    @Override
    public void onClickShowMeal(MealDto meal) {
        com.example.ratatouille.search.view.SearchFragmentDirections.ActionSearchFragmentToMealFragment action=SearchFragmentDirections.actionSearchFragmentToMealFragment(meal);
        Navigation.findNavController(getView()).navigate(action);
    }
}