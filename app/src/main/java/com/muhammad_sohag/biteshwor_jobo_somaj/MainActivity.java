package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView cardViewNotice, cardViewPepole, cardViewAbout, mainCardView;
    RelativeLayout noticeLayout,peopleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        cardIntent();

        anim();

    }

    private void anim() {
        noticeLayout = findViewById(R.id.noticeLayout);
        peopleLayout = findViewById(R.id.people_layout);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_left_to_right);
        noticeLayout.startAnimation(animation);

        animation =AnimationUtils.loadAnimation(this,R.anim.slide_right_to_left);
        peopleLayout.startAnimation(animation);

    }

    private void initToolbar() {
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

                Intent intent = new Intent(MainActivity.this, ChadaActivity.class);
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
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

            }
        });

        //CardView Notice intent
        cardViewNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });


    }

}
