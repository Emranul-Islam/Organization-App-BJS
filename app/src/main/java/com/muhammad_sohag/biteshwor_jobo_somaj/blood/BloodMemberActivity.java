package com.muhammad_sohag.biteshwor_jobo_somaj.blood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.muhammad_sohag.biteshwor_jobo_somaj.R;
import com.muhammad_sohag.biteshwor_jobo_somaj.adapter.BloodAdapter;
import com.muhammad_sohag.biteshwor_jobo_somaj.custom.LoadingDialog;
import com.muhammad_sohag.biteshwor_jobo_somaj.model.BloodModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BloodMemberActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BloodAdapter adapter;
    private List<BloodModel> bloodModelList;
    private LoadingDialog ld;

    private FirebaseFirestore base = FirebaseFirestore.getInstance();
    private CollectionReference ref = base.collection("blood");
    private Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_member);

        Toolbar toolbar = findViewById(R.id.bg_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        String bloodGrope = getIntent().getStringExtra("blood");

        getSupportActionBar().setTitle(bloodGrope+" রক্তদাতাগন");

        query = ref.whereEqualTo("blood", bloodGrope);

        ld = new LoadingDialog(this);

        recyclerView = findViewById(R.id.bm_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bloodModelList = new ArrayList<>();

        showData();


    }

    private void showData() {
        bloodModelList.clear();

        ld.showLoadingDialog();
        query.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                assert queryDocumentSnapshots != null;
                if (!queryDocumentSnapshots.isEmpty()){
                    for (QueryDocumentSnapshot snapshot: queryDocumentSnapshots){
                        BloodModel model = snapshot.toObject(BloodModel.class);
                        bloodModelList.add(model);
                        adapter = new BloodAdapter(BloodMemberActivity.this, bloodModelList);
                        recyclerView.setAdapter(adapter);
                    }
                    ld.dismissLoadingDialog();

                    adapter.notifyDataSetChanged();
                }else{
                    ld.dismissLoadingDialog();
                    Toast.makeText(BloodMemberActivity.this, "Something wrong: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);
        MenuItem menuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setBackgroundColor(Color.WHITE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText.trim())){
                    searchBloodMember(newText);
                }else {
                    showData();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void searchBloodMember(final String newText) {
        bloodModelList.clear();
        query.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                assert queryDocumentSnapshots != null;
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                        BloodModel model = snapshot.toObject(BloodModel.class);
                        if (model.getName().toLowerCase().contains(newText.toLowerCase()) ||
                                model.getThikana().toLowerCase().contains(newText.toLowerCase())) {
                            bloodModelList.add(model);
                        }

                        adapter = new BloodAdapter(BloodMemberActivity.this, bloodModelList);
                        recyclerView.setAdapter(adapter);
                    }

                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(BloodMemberActivity.this, "Something wrong: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
