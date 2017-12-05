package com.example.hasaa.tradeslist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by hasaa on 12/4/2017.
 */

public class item_listing_adapter extends ArrayAdapter<Listing>{

    private FirebaseAuth mAuth;


    public item_listing_adapter(Context context, ArrayList<Listing> objects){
        super(context, 0, objects);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Listing listing = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listing, parent, false);
        }

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String username = user.getEmail();
        String userID = user.getUid();




        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listing, parent, false);
        }

        TextView skill = (TextView) convertView.findViewById(R.id.Title);
        TextView use = (TextView) convertView.findViewById(R.id.User);
        TextView description = (TextView) convertView.findViewById(R.id.Desc);

        skill.setText(listing.getTitle());

        //use.setText(listing.getLister().getEmail()+".edu");

        description.setText(listing.getDescription());




        return convertView;
    }


}
