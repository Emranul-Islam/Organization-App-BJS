package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muhammad_sohag.biteshwor_jobo_somaj.blood.BloodGroups;

public class MainActivity extends AppCompatActivity {


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

        anim();


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




    //blood donner click listener:
    public void bloodDonner(View view) {
        Intent intent = new Intent(this, BloodGroups.class);
        startActivity(intent);
    }



    //people click listener:
    public void people(View view) {
        Intent intent = new Intent(MainActivity.this, People.class);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
        }else {
            startActivity(intent);
        }



    }



    //user click listener:
    public void user(View view) {

        if (cUser != null) {
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }


    //about click listener:
    public void about(View view) {
        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }
}
