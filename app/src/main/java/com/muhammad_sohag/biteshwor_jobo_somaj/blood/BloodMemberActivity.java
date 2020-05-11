package com.muhammad_sohag.biteshwor_jobo_somaj.blood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.muhammad_sohag.biteshwor_jobo_somaj.R;
import com.muhammad_sohag.biteshwor_jobo_somaj.adapter.BloodAdapter;
import com.muhammad_sohag.biteshwor_jobo_somaj.model.BloodModel;

import java.util.ArrayList;
import java.util.List;

public class BloodMemberActivity extends AppCompatActivity {

    private ImageView callBtn;
    private RecyclerView recyclerView;
    private BloodAdapter adapter;
    private List<BloodModel> bloodModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_member);

        Toolbar toolbar = findViewById(R.id.bg_toolbar);
        setSupportActionBar(toolbar);

        String bloodGrope = getIntent().getStringExtra("blood");

        getSupportActionBar().setTitle(bloodGrope+" রক্তদাতাগন");

        recyclerView = findViewById(R.id.bm_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bloodModelList = new ArrayList<>();

        showData();


    }

    private void showData() {
        bloodModelList.add(new BloodModel("Sohag", "01315112", "বিটেশ্বর"));
        bloodModelList.add(new BloodModel("Sohag", "01315112", "মাদলা"));
        bloodModelList.add(new BloodModel("Sohag", "01315112", "বিটেশ্বর"));
        bloodModelList.add(new BloodModel("Sohag", "01315112", "মলয়"));
        bloodModelList.add(new BloodModel("Sohag", "01315112", "বরকোটা"));
        bloodModelList.add(new BloodModel("Sohag", "01315112", "মাদলা"));
        bloodModelList.add(new BloodModel("Sohag", "01315112", "মলয়"));
        bloodModelList.add(new BloodModel("Sohag", "01315112", "Chini"));
        bloodModelList.add(new BloodModel("Sohag", "01315112", "adhj"));
        bloodModelList.add(new BloodModel("Sohag", "01315112", "sdhf"));

        adapter = new BloodAdapter(this, bloodModelList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
