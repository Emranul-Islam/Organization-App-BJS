package com.matrichaya.bjs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.zip.Inflater;

public class pepole extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;
    int[] photo={
            R.drawable.tt1,
            R.drawable.tt2,
            R.drawable.tt3,
            R.drawable.tt4,
            R.drawable.tt5
    };
    String [] names ={};
    String [] telephones ={};

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pepole);

        listView = findViewById(R.id.myListViewID);
        AllWork();
    }

    public void AllWork(){
        listView=(ListView) findViewById(R.id.myListViewID);
        telephones =getResources().getStringArray(R.array.phoneNumber);
        names =getResources().getStringArray(R.array.pepolesName);
        int i=0;

        adapter= new Adapter(getApplicationContext(),R.layout.contact_item);
        listView.setAdapter(adapter);

        for(String titles: names){
            DataProvider dataProvider= new DataProvider(photo[i], titles, telephones[i]);

            adapter.add(dataProvider);
            i++;
        }
    }


}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         