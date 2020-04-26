package com.muhammad_sohag.biteshwor_jobo_somaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChadaActivity extends AppCompatActivity {
    CardView chadaCard;
    private RecyclerView chadaRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chada);
        chadaCard = findViewById(R.id.cardView);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.slide_left_to_right);
        chadaCard.setAnimation(animation);
        List<ChadaModel> values = new ArrayList<>();
        values.add(new ChadaModel("Sohag","12:30"));
        values.add(new ChadaModel("সোহাগ","12:30"));
        values.add(new ChadaModel("Sohag","12:30"));
        values.add(new ChadaModel("Sohag","12:30"));
        values.add(new ChadaModel("Sohag","12:30"));
        values.add(new ChadaModel("Sohag","12:30"));
        values.add(new ChadaModel("Sohag","12:30"));




        chadaRecycler = findViewById(R.id.chad_recycler_view);

        chadaRecycler.setLayoutManager(new LinearLayoutManager(this));
        ChadaAdapter cAdapter = new ChadaAdapter(this,values);
        chadaRecycler.setAdapter(cAdapter);
        cAdapter.notifyDataSetChanged();


    }
}
