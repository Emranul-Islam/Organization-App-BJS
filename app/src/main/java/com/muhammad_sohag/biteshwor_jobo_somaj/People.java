package com.muhammad_sohag.biteshwor_jobo_somaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class People extends AppCompatActivity {

    private String[] names;
    private String[] numbers;
    private RecyclerView peopleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        Toolbar toolbar = findViewById(R.id.people_toolBar);
        setSupportActionBar(toolbar);
        setTitle("People");

        names =getResources().getStringArray(R.array.peoplesName);
        numbers = getResources().getStringArray(R.array.phoneNumber);

        peopleRecyclerView = findViewById(R.id.people_recycler_view);
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(People.this));
        PeopleAdapter peopleAdapter = new PeopleAdapter(People.this,names,numbers);
        peopleRecyclerView.setAdapter(peopleAdapter);

    }
}
