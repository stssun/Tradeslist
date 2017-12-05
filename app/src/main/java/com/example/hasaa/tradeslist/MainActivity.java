package com.example.hasaa.tradeslist;

/**
 * Most of the authentication code is adapted or taken from the official firebase android studio
 * user authentication tutorial on github:
 * https://developers.google.com/identity/sign-in/android/sign-in
 * https://firebase.google.com/docs/auth/android/google-signin
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SignInButton mGoogleButton;
    private GoogleSignInClient mGoogleSignInClient;

    //authentication
    private FirebaseAuth mAuth;

    //constant for sign in request value
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set button
        mGoogleButton = (SignInButton) findViewById(R.id.google_button);
        //set listener
        mGoogleButton.setOnClickListener(this);

        //configure sign-in to request user id, email address and basic profile
        //taken from tutorial
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        //build sign in client with the gso
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //authentication
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart(){
        super.onStart();

        //taken from official tutorial
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        //if non-null, just start the home activity
        if(account != null){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v){
        //do log in and stuff
        //following the tutorial
        switch(v.getId()){
            case R.id.google_button:
                signIn();
                break;
        }
    }

    //taken from tutorial
    //starts the sign-in intent which is the activity with the different email options and
    //asks for permissions
    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //taken from tutorial
    //once the sign in activity is called
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //handleSignInResult(task);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
            }
        }
    }

    //taken from tutorial
    //handles the sign in result
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, go to home activity
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w("MainActivity", "signInResult:failed code=" + e.getStatusCode());
            int temp = e.getStatusCode();
            Toast.makeText(this, "its not working " + temp, Toast.LENGTH_SHORT).show();
            //perform some action since sign-in failed
        }
    }

    //taken from tutorial code
    //authenticate user with firebase
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //ferform some action since sign-in failed
                        }
                    }
                });
    }
}
