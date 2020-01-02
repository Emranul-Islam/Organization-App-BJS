package com.matrichaya.bijos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

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
