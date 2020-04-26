package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.muhammad_sohag.biteshwor_jobo_somaj.custom.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class People extends AppCompatActivity {

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference databaseRaf = database.collection("Sodesso_List");
    private LoadingDialog ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        Toolbar toolbar = findViewById(R.id.people_toolBar);
        ld = new LoadingDialog(this);
        setSupportActionBar(toolbar);
        setTitle("People");

        addRecycler();
    }

    private void addRecycler() {
        ld.showLoadingDialog();
        RecyclerView peopleRecyclerView = findViewById(R.id.people_recycler_view);
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(People.this));
        final List<PeopleModel> modelList = new ArrayList<>();

        final PeopleAdapter peopleAdapter = new PeopleAdapter(People.this, modelList);
        peopleRecyclerView.setAdapter(peopleAdapter);


        databaseRaf.orderBy("name").addSnapshotListener(People.this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(People.this, "Somthing is Wronng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (queryDocumentSnapshots != null) {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                        PeopleModel model = documentSnapshot.toObject(PeopleModel.class);
                        modelList.add(model);

                    }
                    peopleAdapter.notifyDataSetChanged();
                    ld.dismissLoadingDialog();

                }
            }
        });




    }
}
