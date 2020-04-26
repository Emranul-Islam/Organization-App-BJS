package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private CardView cardViewNotice, cardViewPepole, userCardView, mainCardView;
    private ImageView userImage;
    private TextView userName;
    RelativeLayout noticeLayout, peopleLayout;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser cUser = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userImage = findViewById(R.id.user_image);
        userName = findViewById(R.id.user_name);

        initToolbar();
        cardIntent();

        anim();

        //todo: ekhane kicho kaj korbo current user er jonno

    }

    private void anim() {
        noticeLayout = findViewById(R.id.noticeLayout);
        peopleLayout = findViewById(R.id.people_layout);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_left_to_right);
        noticeLayout.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.slide_right_to_left);
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
        userCardView = findViewById(R.id.user);


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
        userCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cUser == null) {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }            }
        });

        //CardView Notice intent
        cardViewNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

}
