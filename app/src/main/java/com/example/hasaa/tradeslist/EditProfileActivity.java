package com.example.hasaa.tradeslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfileActivity extends AppCompatActivity {

    private String phoneName;
    private Button saveProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        saveProf = (Button) findViewById(R.id.savebutton);


        saveProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EditText name = (EditText) findViewById(R.id.edit_profile_name_text);
                phoneName = name.getText().toString();

                EditText phone = (EditText) findViewById(R.id.edit_profile_phone_number);
                phoneName = phone.getText().toString();




                //Following regex formula taken from
                //http://howtodoinjava.com/regex/java-regex-validate-and-format-north-american-phone-numbers/
                String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(phoneName);
                if(matcher.matches() && name.length() > 0){
                    Toast.makeText(getApplicationContext(), "Valid Phone Number", Toast.LENGTH_SHORT).show();

                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
