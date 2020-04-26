package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class About extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initToolbar();



    }



    private void initToolbar(){
        toolbar=findViewById(R.id.adminToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("এডমিন");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
