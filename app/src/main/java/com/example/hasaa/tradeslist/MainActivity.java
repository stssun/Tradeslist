package com.example.hasaa.tradeslist;

/**
 * Most of the authentication code is adapted or taken from the official firebase android studio
 * user authentication tutorial on github:
 * https://developers.google.com/identity/sign-in/android/sign-in?authuser=0
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private SignInButton mGoogleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set button
        mGoogleButton = (SignInButton) findViewById(R.id.google_button);

        //configure sign-in to request user id, email address and basic profile
        //taken from tutorial
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //build sign in client with the gso
        //mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
}
