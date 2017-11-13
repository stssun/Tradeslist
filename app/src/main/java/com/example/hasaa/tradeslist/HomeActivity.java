package com.example.hasaa.tradeslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void view_profile_button(View v) {

        Button b = (Button)v;
        //b.setText("make a string");
        startActivity(new Intent(HomeActivity.this,ViewProfileActivity.class));
    }

    public void post_button(View v) {

        Button b = (Button)v;
        //b.setText("make a string");
        startActivity(new Intent(HomeActivity.this,PostActivity.class));
    }


    public void view_listings_button(View v) {

        Button b = (Button)v;
        //b.setText("make a string");
        startActivity(new Intent(HomeActivity.this,ViewPostsActivity.class));
    }
}
