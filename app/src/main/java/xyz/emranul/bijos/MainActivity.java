package xyz.emranul.bijos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.messaging.FirebaseMessaging;

import xyz.emranul.bijos.blood.BloodGroups;

public class MainActivity extends AppCompatActivity {


    private ImageView userImage;
    private TextView userName;
    private static final String TAG = "MainActivity";

    private final FirebaseUser cUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userImage = findViewById(R.id.user_image);
        userName = findViewById(R.id.user_name);

        initToolbar();

        notificationSubscribe();

        anim();

        profile();

    }

    private void notificationSubscribe() {
        FirebaseMessaging.getInstance().subscribeToTopic("notice")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "msg_subscribed";
                        if (!task.isSuccessful()) {
                            msg = "msg_subscribe_failed";
                        }
                        Log.d(TAG, msg);

                    }
                });
    }

    private void profile() {
        if (cUser != null) {
            DocumentReference data = FirebaseFirestore.getInstance().collection("Sodesso_List").document(cUser.getUid());
            data.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {

                @SuppressLint("CheckResult")
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if (documentSnapshot != null) {
                        RequestOptions requestOptions = new RequestOptions();
                        requestOptions.placeholder(R.drawable.about_icon);
                        Glide.with(MainActivity.this)
                                .setDefaultRequestOptions(requestOptions)
                                .load(documentSnapshot.getString("url"))
                                .into(userImage);
                        userName.setText(documentSnapshot.getString("name"));
                    }
                }
            });
        }
    }

    private void anim() {
        RelativeLayout noticeLayout = findViewById(R.id.noticeLayout);
        RelativeLayout peopleLayout = findViewById(R.id.people_layout);

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

        Intent intent;
        if (cUser != null) {
            intent = new Intent(MainActivity.this, UserActivity.class);
        } else {
            intent = new Intent(MainActivity.this, LoginActivity.class);
        }
        startActivity(intent);
    }

    //about click listener:
    public void about(View view) {
        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }
}
