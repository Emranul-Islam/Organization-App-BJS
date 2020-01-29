package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView cardViewNotice, cardViewPepole, cardViewAbout,mainCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        cardIntent();


    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);
    }

    private void cardIntent() {
        mainCardView = findViewById(R.id.logoCardViewID);
        cardViewNotice = findViewById(R.id.noticeID);
        cardViewPepole = findViewById(R.id.pepoleID);
        cardViewAbout = findViewById(R.id.aboutID);


        mainCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Admin.class);
                startActivity(intent);
            }
        });

        //CardView People intent



        cardViewPepole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, People.class);
                startActivity(intent);
            }
        });

        //CardView About intent
        cardViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);

            }
        });

        //CardView Notice intent
        cardViewNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NoticeActivity.class);
                startActivity(intent);
            }
        });
    }

}
