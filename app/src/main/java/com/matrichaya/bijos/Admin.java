package com.matrichaya.bijos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;


public class Admin extends AppCompatActivity {

    Toolbar toolbar;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initToolbar();

        editText = findViewById(R.id.passID);
        button = findViewById(R.id.passBtnID);

        String pass = editText.getText().toString();
        String myPass = "124";

        if (pass == myPass){
            button.setHint("This is");
        }

    }



    private void initToolbar(){
        toolbar=findViewById(R.id.adminToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("এডমিন");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
