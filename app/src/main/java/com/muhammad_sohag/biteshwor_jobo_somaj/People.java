package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
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
import com.muhammad_sohag.biteshwor_jobo_somaj.adapter.PeopleAdapter;
import com.muhammad_sohag.biteshwor_jobo_somaj.custom.LoadingDialog;
import com.muhammad_sohag.biteshwor_jobo_somaj.model.PeopleModel;

import java.util.ArrayList;
import java.util.List;

public class People extends AppCompatActivity {

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference databaseRaf = database.collection("Sodesso_List");
    private LoadingDialog ld;
    private List<PeopleModel> modelList;
    private PeopleAdapter peopleAdapter;
    private RecyclerView peopleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        Toolbar toolbar = findViewById(R.id.people_toolBar);
        ld = new LoadingDialog(this);
        setSupportActionBar(toolbar);
        setTitle("People");


        peopleRecyclerView = findViewById(R.id.people_recycler_view);
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(People.this));
        modelList = new ArrayList<>();


        loadPeople();
    }


    private void loadPeople() {
        ld.showLoadingDialog();
        databaseRaf.orderBy("name").addSnapshotListener(People.this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                modelList.clear();
                if (e != null) {
                    Toast.makeText(People.this, "Somthing is Wronng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (queryDocumentSnapshots != null) {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                        PeopleModel model = documentSnapshot.toObject(PeopleModel.class);
                        modelList.add(model);

                        peopleAdapter = new PeopleAdapter(People.this, People.this, modelList);
                        peopleRecyclerView.setAdapter(peopleAdapter);

                    }
                    peopleAdapter.notifyDataSetChanged();

                    ld.dismissLoadingDialog();

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        MenuItem menuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (!TextUtils.isEmpty(newText.trim())) {
                    searchPeople(newText);
                } else if (newText.isEmpty()) {
                    loadPeople();
                } else {
                    loadPeople();
                }

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void searchPeople(final String query) {


        databaseRaf.orderBy("name").addSnapshotListener(People.this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                modelList.clear();
                if (e != null) {
                    Toast.makeText(People.this, "Somthing is Wronng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (queryDocumentSnapshots != null) {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                        PeopleModel model = documentSnapshot.toObject(PeopleModel.class);
                        if (model.getName().toLowerCase().contains(query.toLowerCase()) ||
                                model.getBlood().toLowerCase().contains(query.toLowerCase())) {
                            modelList.add(model);
                            
                        }

                        peopleAdapter = new PeopleAdapter(People.this, People.this, modelList);
                        peopleRecyclerView.setAdapter(peopleAdapter);

                    }
                    peopleAdapter.notifyDataSetChanged();


                }
            }
        });
    }
}
