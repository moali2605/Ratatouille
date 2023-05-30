package com.example.ratatouille.profile;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouille.Activity.MainActivity;
import com.example.ratatouille.Network.MealClient;
import com.example.ratatouille.R;
import com.example.ratatouille.db.ConcreteLocalSource;
import com.example.ratatouille.model.MealDto;
import com.example.ratatouille.model.Repository;
import com.example.ratatouille.model.UserDto;
import com.example.ratatouille.profile.presenter.ProfilePresenter;
import com.example.ratatouille.profile.view.ProfileInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileFragment extends Fragment implements ProfileInterface {
    ImageView ivProfile;
    TextView tvProfile;
    Button btnAboutApp, btnLogout;
    FirebaseUser currentUser;
    FirebaseFirestore db;
    List<MealDto> meals;
    ProfilePresenter profilePresenter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        ivProfile = v.findViewById(R.id.ivProfile);
        tvProfile = v.findViewById(R.id.tvProfile);
        btnAboutApp = v.findViewById(R.id.btnAboutApp);
        btnLogout = v.findViewById(R.id.btnLogout);
        profilePresenter = new ProfilePresenter(Repository.getInstance(MealClient.getInstance(), ConcreteLocalSource.getInstance(getContext()), getContext()), this);
        profilePresenter.getFavMeals();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();


        if (currentUser != null) {
            String name = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            Uri photoUrl = currentUser.getPhotoUrl();
            tvProfile.setText(name);
            Glide.with(getContext()).load(photoUrl)
                    .apply(new RequestOptions().override(250, 250))
                    .placeholder(R.drawable.profile)
                    .error(R.drawable.profile).into(ivProfile);
            boolean emailVerified = currentUser.isEmailVerified();
            String uid = currentUser.getUid();
        }
        btnLogout.setOnClickListener(v1 -> {
            updateUserDataInFireStore();
            profilePresenter.deleteAllMeals();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
        btnAboutApp.setOnClickListener(v1 -> {
            showDialog();
        });
    }

    private void updateUserDataInFireStore() {
        UserDto updatedUser = new UserDto(currentUser.getEmail(), meals);
        Map<String, Object> data = new HashMap<>();
        data.put("users", updatedUser);
        db.collection("users")
                .document(currentUser.getUid())
                .set(data, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("hey", "User updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("hey", "Error updating user", e);
                    }
                });
    }

    public void showDialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.about_app_dialog);
        dialog.show();
    }

    @Override
    public void onGetFavouriteMeal(List<MealDto> mealDto) {
        meals = mealDto;
        Log.i("hey", "onGetFavouriteMeal: " + meals.size());
    }
}