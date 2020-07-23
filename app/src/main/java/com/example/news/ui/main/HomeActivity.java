package com.example.news.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.news.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button signin = findViewById(R.id.Signin);
        Button register = findViewById(R.id.Register);
        Button guest = findViewById(R.id.Guest);
        final Context context = this;

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // User is signed in

            Log.d(this.getClass().getName(), "User is Signed in");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }


          //Listener When user change Email
        mAuthListener = firebaseAuth -> {
            FirebaseUser user1 = firebaseAuth.getCurrentUser();
            if (user1 != null) {
                // User is signed in
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                Log.d("XX AuthChang:signedin:2", "");
            } else {
                // User is signed out
                Log.d("XX AuthChang:signed_out", "");
            }
        };




        signin.setOnClickListener(view -> {
            Intent intent = new Intent(context, SignInActivity.class);
            startActivity(intent);


        });

        register.setOnClickListener(view -> {
            Intent intent = new Intent(context, RegisterActivity.class);
            startActivity(intent);


        });
        guest.setOnClickListener(view -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
