package com.muhammad_sohag.biteshwor_jobo_somaj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.muhammad_sohag.biteshwor_jobo_somaj.custom.LoadingDialog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    private EditText name,number1,number2;
    private Button btn;
    private FirebaseFirestore refDatabase = FirebaseFirestore.getInstance();
    private DocumentReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final String uid = getIntent().getStringExtra("uid");
        dataBase = refDatabase.collection("Sodesso_List").document(uid);

        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.showLoadingDialog();


        name = findViewById(R.id.edit_name);
        number1 = findViewById(R.id.edit_number1);
        number2 = findViewById(R.id.edit_number2);
        btn = findViewById(R.id.edit_btn);

        dataBase.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null){
                    name.setText(documentSnapshot.getString("name"));
                    number1.setText(documentSnapshot.getString("number"));
                    number2.setText(documentSnapshot.getString("number2"));
                    loadingDialog.dismissLoadingDialog();
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.showLoadingDialog();
                btn.setClickable(false);
                String nameString = name.getText().toString();
                String number1String = number1.getText().toString();
                String number2String = number2.getText().toString();

                Map<String, Object> value = new HashMap<>();
                value.put("name", nameString);
                value.put("number", number1String);
                value.put("number2", number2String);

                dataBase.update(value).addOnCompleteListener(UpdateActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()){
                            btn.setClickable(true);
                            loadingDialog.dismissLoadingDialog();
                            Toast.makeText(UpdateActivity.this, "ডাটা পরিবর্তন হয়েছে !", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(UpdateActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissLoadingDialog();
                            btn.setClickable(true);
                        }
                    }
                });
            }
        });



    }

}
