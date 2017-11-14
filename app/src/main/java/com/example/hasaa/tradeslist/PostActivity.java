package com.example.hasaa.tradeslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostActivity extends AppCompatActivity {


    private Button saveProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

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
}
