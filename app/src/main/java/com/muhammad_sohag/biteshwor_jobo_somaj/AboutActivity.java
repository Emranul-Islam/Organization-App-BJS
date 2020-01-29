package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initToolbar();
    }

    private void initToolbar(){
        toolbar=findViewById(R.id.AboutToolbarID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.aboutUs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
