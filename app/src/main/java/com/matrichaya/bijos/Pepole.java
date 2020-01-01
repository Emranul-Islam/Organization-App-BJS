package com.matrichaya.bijos;

import android.os.Bundle;

import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Pepole extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;


    int[] photo={
            R.drawable.sohag,
            R.drawable.tt2,
            R.drawable.tt3,
            R.drawable.tt4,
            R.drawable.tt1
    };
    String [] names ={};
    String [] telephones ={};

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pepole);

        listView = findViewById(R.id.myListViewID);
        initToolbar();
        AllWork();





    }



    private void initToolbar(){
        toolbar=findViewById(R.id.pepoleToolbarID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.pepole);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void AllWork(){
        listView = findViewById(R.id.myListViewID);
        telephones = getResources().getStringArray(R.array.phoneNumber);
        names = getResources().getStringArray(R.array.pepolesName);
        int i=0;

        adapter= new Adapter(getApplicationContext(),R.layout.contact_item);
        listView.setAdapter(adapter);

        for(String titles: names){
            DataProvider dataProvider= new DataProvider(photo[i], titles, telephones[i]);

            adapter.add(dataProvider);
            i++;
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);

        MenuItem menuItem =  findViewById(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }*/

}
