package com.example.hasaa.tradeslist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by hasaa on 12/5/2017.
 */

public class item_listing_no_edit extends ArrayAdapter<Listing> {


    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mListingRef;

    private ArrayList<Listing> list;


    public item_listing_no_edit(Context context, ArrayList<Listing> objects){
        super(context, 0, objects);

    }

    public View getView(final int position, View convertView, ViewGroup parent) {


        Listing listing = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listing_no_edit, parent, false);
        }


        Button viewitem = (Button)convertView.findViewById(R.id.itemlistingviewbutton);
        //Button addBtn = (Button)convertView.findViewById(R.id.add_btn);

        viewitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context ctx = getContext();

                mAuth = FirebaseAuth.getInstance();

                FirebaseUser user = mAuth.getCurrentUser();
                final String username = user.getEmail();
                final String userID = user.getUid();

                mDatabase = FirebaseDatabase.getInstance();
                mListingRef = mDatabase.getReference();

                mListingRef.addListenerForSingleValueEvent((new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        GenericTypeIndicator<ArrayList<Listing>> t = new GenericTypeIndicator<ArrayList<Listing>>() {};
                        list = dataSnapshot.child("listing").child(userID).getValue(t);
                        Log.i("from listing adapter", list.get(position).getTitle());

                        Profile profileObj = dataSnapshot.child("profile").child(userID).getValue(Profile.class);

                        int temp = list.size();
                        temp = temp - position - 1;

                        Context ctx = getContext();
                        Intent intent = new Intent(ctx, ViewOneListingEDIT.class);
                        intent.putExtra("phone", list.get(temp).getPhone());
                        intent.putExtra("ID", list.get(temp).getEmail());
                        intent.putExtra("title", list.get(temp).getTitle());
                        intent.putExtra("description", list.get(temp).getDescription());
                        intent.putExtra("request", list.get(temp).getRequest());
                        ctx.startActivity(intent);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }));

            }
        });

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String username = user.getEmail();
        String userID = user.getUid();


        TextView skill = (TextView) convertView.findViewById(R.id.Title);
        TextView use = (TextView) convertView.findViewById(R.id.User);
        TextView description = (TextView) convertView.findViewById(R.id.Desc);

        skill.setText(listing.getTitle());

        use.setText(listing.getEmail());

        description.setText(listing.getDescription());

        return convertView;
    }


}
