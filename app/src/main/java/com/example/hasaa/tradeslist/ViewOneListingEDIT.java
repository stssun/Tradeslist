package com.example.hasaa.tradeslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewOneListingEDIT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_one_listing_edit);

        String userID = getIntent().getStringExtra("ID");
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String request = getIntent().getStringExtra("request");
        String phone = getIntent().getStringExtra("phone");

        TextView temp = (TextView) findViewById(R.id.skillnametex);
        temp.setText(title);

        temp = (TextView) findViewById(R.id.descriptiontex);
        temp.setText(description);

        temp = (TextView) findViewById(R.id.requesttex);
        temp.setText(request);

        temp = (TextView) findViewById(R.id.email);
        temp.setText(userID);

        temp = (TextView) findViewById(R.id.phone);
        temp.setText(phone);


    }
}
