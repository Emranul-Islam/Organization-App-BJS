package xyz.emranul.bijos;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Objects;

import xyz.emranul.bijos.custom.LoadingDialog;


public class NoticeActivity extends AppCompatActivity {


    private final CollectionReference database = FirebaseFirestore.getInstance().collection("Notice");
    private TextView noticeText;
    private LoadingDialog ld;
    private final FirebaseUser cUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeText = findViewById(R.id.notice_text);
        ld = new LoadingDialog(this);

        initToolbar();
        notice();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.NoticeToolbarID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.notice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void notice() {
        ld.showLoadingDialog();
        Query query = database.orderBy("time", Query.Direction.ASCENDING);
        query.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    StringBuilder data = new StringBuilder();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(Long.parseLong(Objects.requireNonNull(documentSnapshot.getString("time"))));
                        String time = DateFormat.format("dd-MM-yyyy hh:mm aa", cal).toString();

                        data.append("নোটিশ পাঠিয়েছেন: ").append(documentSnapshot.getString("name")).append("\n\nনোটিশ: ")
                                .append(documentSnapshot.getString("massage")).append("\n\nসময়: ")
                                .append(time).append("\n --------------------------\n\n\n");

                    }


                    if (!data.toString().isEmpty()) {
                        noticeText.setText(data.toString());
                    } else {
                        noticeText.setText(R.string.notice_loading);
                    }
                    ld.dismissLoadingDialog();
                } else {
                    ld.dismissLoadingDialog();
                    Toast.makeText(NoticeActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (cUser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
