package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.muhammad_sohag.biteshwor_jobo_somaj.adapter.ChadaAdapter;
import com.muhammad_sohag.biteshwor_jobo_somaj.model.ChadaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserActivity extends AppCompatActivity {
    Toolbar toolbar;

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();
    private final String UID = Objects.requireNonNull(user).getUid();
    private FirebaseFirestore firebase = FirebaseFirestore.getInstance();
    private DocumentReference dataRef = firebase.collection("Sodesso_List").document(UID);
    private CollectionReference chadaRef = firebase.collection("Sodesso_List").document(UID).collection("Chada_2020");

    private ImageView profileImage;
    private TextView profileName, profileNumber1, profileNumber2, bloodGroup, chadaDeya, chadaBaki;
    private Button logout;
    private CardView cardDeya, cardBaki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        profileImage = findViewById(R.id.profile_img);
        profileName = findViewById(R.id.profile_name);
        profileNumber1 = findViewById(R.id.profile_number_1);
        profileNumber2 = findViewById(R.id.profile_number_2);
        bloodGroup = findViewById(R.id.profile_blood_group);
        chadaDeya = findViewById(R.id.chada_deya);
        chadaBaki = findViewById(R.id.chada_baki);
        logout = findViewById(R.id.profile_logout);
        cardDeya = findViewById(R.id.card_chada_deya);
        cardBaki = findViewById(R.id.card_chada_baki);


        loadData();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                finish();
            }
        });

        cardDeya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chadaDetails();
            }
        });
        cardBaki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chadaDetails();
            }
        });

    }

    private void loadData() {
        dataRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @SuppressLint("CheckResult")
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot != null) {

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.placeholder(R.drawable.profile_ic);
                    Glide.with(UserActivity.this)
                            .setDefaultRequestOptions(requestOptions)
                            .load(documentSnapshot.getString("url"))
                            .into(profileImage);
                    profileName.setText(documentSnapshot.getString("name"));
                    profileNumber1.setText(documentSnapshot.getString("number"));
                    profileNumber2.setText(documentSnapshot.getString("number2"));
                    bloodGroup.setText(String.format("রক্তের গ্রুপ: %s", documentSnapshot.getString("blood")));

                } else {
                    Toast.makeText(UserActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //chada count
        chadaRef.addSnapshotListener(UserActivity.this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                try {
                    int deya = queryDocumentSnapshots.size();
                    int baki = 12 - deya;
                    chadaDeya.setText(deya + "");
                    chadaBaki.setText(baki + "");
                } catch (Exception exception) {
                    Toast.makeText(UserActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void chadaDetails() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(this).inflate(
                R.layout.bottom_sheet_layout,(LinearLayout) findViewById(R.id.bottom_sheet_container)
        );

        final RecyclerView recyclerView = bottomSheetView.findViewById(R.id.bottom_sheet_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<ChadaModel> chadaModelsList = new ArrayList<>();
        final ChadaAdapter adapter = new ChadaAdapter(this, chadaModelsList);

        chadaRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null){
                    for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                        ChadaModel chadaModel = documentSnapshot.toObject(ChadaModel.class);
                        chadaModelsList.add(chadaModel);
                        recyclerView.setAdapter(adapter);
                    }

                    adapter.notifyDataSetChanged();
                }
            }
        });


        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();



    }


    public void edit(View view) {
        Intent intent = new Intent(this,UpdateActivity.class);
        intent.putExtra("uid", UID);
        startActivity(intent);
    }
}
