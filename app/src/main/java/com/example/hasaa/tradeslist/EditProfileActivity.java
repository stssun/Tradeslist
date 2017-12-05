package com.example.hasaa.tradeslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class EditProfileActivity extends AppCompatActivity {

    private String phoneName;
    private String name;
    private Button saveProf;

    //Database Refs
    private FirebaseDatabase mDatabase;
    private DatabaseReference mProfileRef;
    private DatabaseReference mNewProfileRef;

    private FirebaseAuth mAuth;

    //var database = firebase.database();

    public final Map<String, String> users = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String username = user.getEmail();

        TextView tv = (TextView)findViewById(R.id.edit_profile_email_text);
        tv.setText(username);

        saveProf = (Button) findViewById(R.id.savebutton);

       // mDatabase = FirebaseDatabase.getInstance();
       // mProfileRef = mDatabase.getReference().child("profile");

        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EditText nameedit = (EditText) findViewById(R.id.edit_profile_name_text);
                name = nameedit.getText().toString();

                EditText phone = (EditText) findViewById(R.id.edit_profile_phone_number);
                phoneName = phone.getText().toString();


                //saveProf = (Button) findViewById(R.id.savebutton);

                saveProf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //Following regex formula taken from
                        //http://howtodoinjava.com/regex/java-regex-validate-and-format-north-american-phone-numbers/
                        String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(phoneName);
                        if (matcher.matches() && name.length() > 0) {



                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(EditProfileActivity.this, ViewProfileActivity.class));

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference().child("profile").child("Name");
                            myRef.setValue(name);

                            myRef = database.getReference().child("profile").child("Phone");

                            myRef.setValue(phoneName);

                            /*mNewProfileRef = mDatabase.getReference().child("profiles");
                            users.put("usertest", "testinput");
                            mNewProfileRef.setValue(users);*/


                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
