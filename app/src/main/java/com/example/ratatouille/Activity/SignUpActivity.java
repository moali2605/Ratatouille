package com.example.ratatouille.Activity;

import static com.example.ratatouille.home.view.HomeFragment.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ratatouille.Activity.Home.presenter.FirebasePresenter;
import com.example.ratatouille.Network.MealClient;
import com.example.ratatouille.R;
import com.example.ratatouille.db.ConcreteLocalSource;
import com.example.ratatouille.model.Repository;
import com.example.ratatouille.model.UserDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText tfEmail, tfName, tfPassword, tfConfPassword;
    Button btnSignup;
    String TAG = "signUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        tfEmail = findViewById(R.id.tfEmailSignIn);
        tfName = findViewById(R.id.tfName);
        tfPassword = findViewById(R.id.tfPasswordSignIn);
        tfConfPassword = findViewById(R.id.tfConPassword);
        btnSignup = findViewById(R.id.btnSignInAuth);

        btnSignup.setOnClickListener(v -> {
            mAuth.createUserWithEmailAndPassword(tfEmail.getText().toString(), tfPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                mAuth.getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(tfName.getText().toString()).build());
                                Toast.makeText(SignUpActivity.this, "Authentication Done.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                                finish();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                                //updateUI(null);
                            }
                        }
                    });
        });
    }

}