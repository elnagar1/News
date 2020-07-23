package com.example.news.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.news.R;
import com.example.news.utils.InternetConnectionChecker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //  private TextView Detail;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;

    private EditText Email;
    private EditText Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        textViewSignup = findViewById(R.id.textViewSignUp);
        progressDialog = new ProgressDialog(this);

        textViewSignup.setOnClickListener(v ->
                startActivity(new Intent(getBaseContext(), RegisterActivity.class))
        );


        mAuth = FirebaseAuth.getInstance();
        // Buttons
        mAuthListener = firebaseAuth -> {

            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }

        };


        findViewById(R.id.SigninButton).setOnClickListener(this);


    }


    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }


        // [START sign_in_with_email]

        progressDialog.setMessage(getString(R.string.singinnow));
        progressDialog.show();
        progressDialog.setCancelable(false);

        mAuth.signInWithEmailAndPassword(Email.getText().toString().trim(),
                Password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        InternetConnectionChecker internetConnectionChecker = new InternetConnectionChecker(getApplicationContext());
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();


                        } else {
                            if (internetConnectionChecker.isInternetOn()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                progressDialog.dismiss();
                                Toast.makeText(SignInActivity.this, R.string.wrong,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                Log.w(TAG, "signInWithEmail:failed ", task.getException());
                                Toast.makeText(SignInActivity.this, R.string.no_internet,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        progressDialog.dismiss();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        // hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]

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

    private boolean validateForm() {
        boolean valid = true;

        String email = Email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Email.setError("Required.");
            valid = false;
        } else {
            Email.setError(null);
        }

        String password = Password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            Password.setError("Required.");
            valid = false;
        } else {
            Password.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.SigninButton) {
            String s = Email.getText().toString().trim();
            String ss = Password.getText().toString().trim();
            signIn(s, ss);

        }

    }

}
