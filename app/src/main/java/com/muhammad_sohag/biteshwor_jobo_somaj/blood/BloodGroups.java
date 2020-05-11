package com.muhammad_sohag.biteshwor_jobo_somaj.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.muhammad_sohag.biteshwor_jobo_somaj.R;

public class BloodGroups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_groups);
    }

    public void a_pos(View view) {
        toIntent("A+");
    }

    public void a_nag(View view) {
        toIntent("A-");
    }

    public void ab_pos(View view) {
        toIntent("AB+");
    }

    public void ab_nag(View view) {
        toIntent("AB-");
    }

    public void b_pos(View view) {
        toIntent("B+");
    }

    public void b_nag(View view) {
        toIntent("B-");
    }

    public void o_pos(View view) {
        toIntent("O+");
    }

    public void o_nag(View view) {

        toIntent("O-");

    }

    private void toIntent(String bloodGroup) {
        Intent intent = new Intent(this, BloodMemberActivity.class);
        intent.putExtra("blood",bloodGroup);
        startActivity(intent);

    }


}
