package com.example.hasaa.tradeslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }


    public void post_button(View v) {

        Button b = (Button)v;
        //b.setText("make a string");
//        startActivity(new Intent(PostActivity.this,HomeActivity.class));
        finish();
    }

}
