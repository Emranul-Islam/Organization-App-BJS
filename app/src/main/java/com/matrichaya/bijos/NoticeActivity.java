package com.matrichaya.bijos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class NoticeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        initToolbar();
    }

    private void initToolbar(){
        toolbar=findViewById(R.id.NoticeToolbarID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.notice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
