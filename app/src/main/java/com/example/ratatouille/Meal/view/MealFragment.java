package com.example.ratatouille.Meal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.ratatouille.Meal.presenter.MealPresenter;
import com.example.ratatouille.R;
import com.example.ratatouille.model.MealDto;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MealFragment extends Fragment implements ViewMealInterface {
    MealDto meal;
    ImageView ivMeal;
    TextView tvMeal, tvCountry, tvCatogry, tvDescription;

    MealIngredientAdapter ingredientAdapter;
    MealPresenter mealPresenter;
    RecyclerView recyclerView;
    YouTubePlayerView playerView;
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
        //meal = MealFragmentArgs.fromBundle(getArguments()).getMeal();
        ivMeal = v.findViewById(R.id.ivMeal);
        tvMeal = v.findViewById(R.id.tvMeal);
        tvCatogry = v.findViewById(R.id.tvCatogry);
        tvCountry = v.findViewById(R.id.tvCountry);
        tvDescription = v.findViewById(R.id.tvDescription);
        playerView=v.findViewById(R.id.videoPlayer);
        Glide.with(getContext()).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(400, 250))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(ivMeal);
        tvMeal.setText(meal.getStrMeal());
        tvCountry.setText(meal.getStrArea());
        tvCatogry.setText(meal.getStrCategory());
        tvDescription.setText(meal.getStrInstructions());

        if (!meal.getStrYoutube().isEmpty()) {
            playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = extractVideoIdFromUrl(meal.getStrYoutube());
                    youTubePlayer.loadVideo(videoId, 0);
                    youTubePlayer.pause();
                }
            });
        }

        recyclerView=v.findViewById(R.id.rvIngredient);
        LinearLayoutManager linearLayoutManagerIngredient = new LinearLayoutManager(getContext());
        linearLayoutManagerIngredient.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagerIngredient);
        ingredientAdapter=new MealIngredientAdapter(getContext());
        recyclerView.setAdapter(ingredientAdapter);

        mealPresenter.getMeal(meal);


    }

    public static String extractVideoIdFromUrl(String url) {
        String videoId = null;
        Pattern pattern = Pattern.compile("(?<=v(=|/))([\\w-]+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }
    @Override
    public void getMeal(MealDto meal) {
        ingredientAdapter.setList(meal);
        ingredientAdapter.notifyDataSetChanged();
    }
}