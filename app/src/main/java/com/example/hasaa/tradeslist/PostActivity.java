package com.example.hasaa.tradeslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    private Button saveProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        //initialize firebase reference and auth reference
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        final String email = user.getEmail();
        String userID = user.getUid();


        saveProf = (Button) findViewById(R.id.postbutton);


        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EditText titleedit= (EditText) findViewById(R.id.skillnametext);
                String title = titleedit.getText().toString();

                EditText descriptionedit= (EditText) findViewById(R.id.descriptiontext);
                String description = descriptionedit.getText().toString();

                EditText requestedit= (EditText) findViewById(R.id.requesttext);
                String request = requestedit.getText().toString();


                if(title.length() > 0 && description.length() > 0 && request.length() > 0){
                    Listing post = new Listing(title, description, request, "1231231234",email);

                    addListing(post, mAuth.getCurrentUser().getUid());


                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    finish();

                }
                else if (!(title.length() > 0)){
                    Toast.makeText(getApplicationContext(), "Invalid Title", Toast.LENGTH_SHORT).show();
                }
                else if (!(description.length() > 0)){
                    Toast.makeText(getApplicationContext(), "Invalid Description", Toast.LENGTH_SHORT).show();
                }
                //if (!(request.length() > 0)){
                else{
                    Toast.makeText(getApplicationContext(), "Invalid Request", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    public void addListing(final Listing post, final String userid){

        final DatabaseReference listings = mDatabase.child("listing");
        listings.addListenerForSingleValueEvent((new ValueEventListener() {
            ArrayList<Listing> list;

            GenericTypeIndicator<ArrayList<Listing>> t = new GenericTypeIndicator<ArrayList<Listing>>() {};

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(userid).exists()){
                    //if user already has listings
                    list = dataSnapshot.child(userid).getValue(t);
                }else{
                    //create new listings ArrayList
                    list = new ArrayList<Listing>();
                }
                //add the listing
                list.add(post);

                //save to database
                listings.child(userid).setValue(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }));

    }
}
