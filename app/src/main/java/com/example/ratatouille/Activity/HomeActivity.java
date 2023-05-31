package com.example.ratatouille.Activity;

import static com.example.ratatouille.home.view.HomeFragment.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ratatouille.Activity.Home.presenter.FirebasePresenter;
import com.example.ratatouille.FavouriteMeal.view.FavouriteMealFragment;
import com.example.ratatouille.Network.MealClient;
import com.example.ratatouille.R;
import com.example.ratatouille.db.ConcreteLocalSource;
import com.example.ratatouille.model.Repository;
import com.example.ratatouille.model.UserDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class HomeActivity extends AppCompatActivity {
    public BottomNavigationView bottomNavigationBar;
    NavController navController;

    FirebasePresenter firebasePresenter;
    FirebaseFirestore db;
    FirebaseUser currentUser;
    UserDto userDto;
    Boolean isExist;
    private PublishSubject<Boolean> stopTrigger = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebasePresenter = new FirebasePresenter(Repository.getInstance(MealClient.getInstance(), ConcreteLocalSource.getInstance(this), this));
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        isExist = false;
        if (currentUser != null) {
            checkDataInFireStore();
        }
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationBar, navController);

        if(checkConnectivity()==false) {
            FavouriteMealFragment fragment = new FavouriteMealFragment();
            fragment.setArguments(new Bundle());
            navController.navigate(R.id.favouriteMealFragment);
            Menu menu = bottomNavigationBar.getMenu();
            MenuItem menuItem = menu.findItem(R.id.homeFragment);
            MenuItem menuItem2 = menu.findItem(R.id.searchFragment);
            MenuItem menuItem3 = menu.findItem(R.id.profileFragment);
            menuItem.setEnabled(false);
            menuItem2.setEnabled(false);
            menuItem3.setEnabled(false);

        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    private void checkDataInFireStore() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getId().equals(currentUser.getUid())) {
                                    //her you need to contain data from FireStore to your object
                                    Map<String, Object> data = document.getData();
                                    Log.i(TAG, "onComplete: " + document.getData());
                                    userDto = new UserDto((Map<String, Object>) data.get("users"));
                                    if (userDto.getFavMeal() != null)
                                        firebasePresenter.insertAllMeal(userDto.getFavMeal());
                                    isExist = true;
                                }
                            }
                            if (!isExist) {
                                createNewUserInFireStore();
                            }
                        } else {
                            Log.d("hey", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void createNewUserInFireStore() {
        Map<String, Object> user = new HashMap<>();
        UserDto newUser = new UserDto(currentUser.getEmail());
        user.put("users", newUser);

        db.collection("users")
                .document(currentUser.getUid())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("hey", "new User Added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("hey", "Error adding document", e);
                    }
                });
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        boolean isConnected = networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        return isConnected;
    }

}