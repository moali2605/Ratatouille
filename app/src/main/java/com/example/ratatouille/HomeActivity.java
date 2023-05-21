package com.example.ratatouille;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationBar;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNavigationBar.setOnItemReselectedListener((NavigationBarView.OnItemReselectedListener) item -> {
            if (item.getItemId() == R.id.item_home) {
                navController.navigate(R.id.homeFragment);
            } else if (item.getItemId() == R.id.item_search) {
                navController.navigate(R.id.searchFragment);
            } else if (item.getItemId() == R.id.item_daily) {
                navController.navigate(R.id.dailyMealFragment);
            } else if (item.getItemId() == R.id.item_fav) {
                navController.navigate(R.id.favouriteMealFragment);
            } else if (item.getItemId() == R.id.item_profile) {
                navController.navigate(R.id.profileFragment);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}