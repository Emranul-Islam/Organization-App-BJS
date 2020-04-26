package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import java.util.Objects;

public class UserActivity extends AppCompatActivity {
     Toolbar toolbar;

   // private FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();
//    private final String UID = Objects.requireNonNull(user).getUid();
//    private FirebaseFirestore database = FirebaseFirestore.getInstance();
//    private DocumentReference dataRef = database.collection("users").document(UID);
//    private CollectionReference Ref = database.collection("users");

    private ImageView profileImage;
    private TextView profileName, chadaDeya, chadaBaki;
    private Button details,edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        profileImage = findViewById(R.id.profile_img);
        profileName = findViewById(R.id.profile_name);
        chadaDeya = findViewById(R.id.chada_deya);
        chadaBaki = findViewById(R.id.chada_baki);
        details = findViewById(R.id.profile_details_btn);
        edit = findViewById(R.id.profile_edit_btn);
        //loadData();
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserActivity.this, ChadaActivity.class);
                startActivity(i);
            }
        });

    }

//    private void loadData() {
//        dataRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                if (documentSnapshot.exists()){
//                    Glide.with(UserActivity.this)
//                            .load(documentSnapshot.getString("pImage"))
//                            .into(profileImage);
//                    profileName.setText(documentSnapshot.getString("pName"));
//
//                    Ref.addSnapshotListener(UserActivity.this, new EventListener<QuerySnapshot>() {
//                        @Override
//                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                            try {
//                                int deya = queryDocumentSnapshots.size();
//                                int baki = deya-12;
//                                chadaDeya.setText(deya+"");
//                                chadaBaki.setText(baki+"");
//                            }catch (Exception exception){
//                                Toast.makeText(UserActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//
//
//                }else{
//                    Toast.makeText(UserActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }


}
