package com.example.hasaa.tradeslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewPostsActivity extends AppCompatActivity {


    public DataSnapshot listings;

    //Database Refs
    private FirebaseDatabase mDatabase;
    private DatabaseReference mListingRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String username = user.getEmail();
        String userID = user.getUid();

        //Database References
        mDatabase = FirebaseDatabase.getInstance();

        //mListingRef = mDatabase.getReference().child("listing").child(userID);
        mListingRef = mDatabase.getReference().child("listing");

        ArrayList<Listing> arrayOfListings = new ArrayList<Listing>();
        final item_listing_no_edit adapter = new item_listing_no_edit(this, arrayOfListings);

        ListView listView= (ListView) findViewById(R.id.listings);
        listView.setAdapter(adapter);

        mListingRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Listing post = snapshot.getValue(Listing.class);
                    adapter.insert(post, 0);
                }


//                Listing post= dataSnapshot.getValue(Listing.class);
//                //ProfileEntry lister= post.getLister();
//                adapter.insert(post, 0);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        View empty = findViewById(R.id.empty);
        ListView list = (ListView) findViewById(R.id.listings);
        list.setEmptyView(empty);
    }
}