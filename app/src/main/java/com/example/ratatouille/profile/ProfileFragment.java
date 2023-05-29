package com.example.ratatouille.profile;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouille.Activity.HomeActivity;
import com.example.ratatouille.Activity.MainActivity;
import com.example.ratatouille.Activity.SignActivity;
import com.example.ratatouille.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    ImageView ivProfile;
    TextView tvProfile;
    Button btnAboutApp,btnLogout;

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
        ivProfile=v.findViewById(R.id.ivProfile);
        tvProfile=v.findViewById(R.id.tvProfile);
        btnAboutApp=v.findViewById(R.id.btnAboutApp);
        btnLogout=v.findViewById(R.id.btnLogout);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            tvProfile.setText(name);
            Glide.with(getContext()).load(photoUrl)
                    .apply(new RequestOptions().override(250, 250))
                    .placeholder(R.drawable.profilphoto)
                    .error(R.drawable.profilphoto).into(ivProfile);
            boolean emailVerified = user.isEmailVerified();
            String uid = user.getUid();
        }
        btnLogout.setOnClickListener(v1 -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
        btnAboutApp.setOnClickListener(v1 -> {
            showDialog();
        });
    }

    public void showDialog() {
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.about_app_dialog);
        dialog.show();
    }
}