package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.muhammad_sohag.biteshwor_jobo_somaj.custom.LoadingDialog;

public class LoginActivity extends AppCompatActivity {

    private EditText logEmail, logPass;
    private Button logBtn;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private LoadingDialog ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logEmail = findViewById(R.id.login_email);
        logPass = findViewById(R.id.login_pass);
        logBtn = findViewById(R.id.login_btn);

        ld = new LoadingDialog(this);



        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = logEmail.getText().toString();
                String password = logPass.getText().toString();

                if (!email.isEmpty()) {
                    if (!password.isEmpty()) {
                        ld.showLoadingDialog();
                        doLogWork(email, password);
                    } else {
                        logPass.setError("পাসওয়ার্ড ঠিক ভাবে দিন!");
                    }
                } else {
                    logEmail.setError("ইমেইল দিন আগে !");
                }
            }
        });
    }

    private void doLogWork(String email, String pass) {
        String fEmail = email+"@bijos.com";
        auth.signInWithEmailAndPassword(fEmail.toLowerCase(), pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login success!!", Toast.LENGTH_SHORT).show();
                            ld.dismissLoadingDialog();
                            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            ld.dismissLoadingDialog();
                            Toast.makeText(LoginActivity.this, "Error Login:" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        ld.dismissLoadingDialog();
                        Toast.makeText(LoginActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
