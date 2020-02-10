package com.muhammad_sohag.biteshwor_jobo_somaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class People extends AppCompatActivity {

    private String[] names;
    private String[] numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        Toolbar toolbar = findViewById(R.id.people_toolBar);
        setSupportActionBar(toolbar);
        setTitle("People");

        names =getResources().getStringArray(R.array.peoplesName);
        numbers = getResources().getStringArray(R.array.phoneNumber);

       addRecycler();
    }

    private void addRecycler(){
        RecyclerView peopleRecyclerView = findViewById(R.id.people_recycler_view);
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(People.this));
        List<PeopleModel> modelList = new ArrayList<>();
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        modelList.add(new PeopleModel("Photo","Name","phone number","UserID"));
        PeopleAdapter peopleAdapter = new PeopleAdapter(People.this,modelList);
        peopleRecyclerView.setAdapter(peopleAdapter);
        peopleAdapter.notifyDataSetChanged();

    }
}
