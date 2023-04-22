package com.example.minhtuan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class main_air extends AppCompatActivity {
    DatabaseReference ND1;
    TextView nhietdo;
    TextView ht;
    ProgressBar Bar;
    String a;
    CheckBox fan,diy,cold;
    float b;
    int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_air);

        nhietdo = findViewById(R.id.nhietdo1);
        Bar = findViewById(R.id.nd2);
        ht = findViewById(R.id.ht);
        fan = findViewById(R.id.fan);
        diy = findViewById(R.id.diy);
        cold = findViewById(R.id.cold);
        cold.setChecked(true);
        diy.setChecked(true);
        fan.setChecked(true);

        //
        //Bar.setProgress(50);
        ND1 = FirebaseDatabase.getInstance().getReference();
        ND1.child("NHIET_DO").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                a = snapshot.getValue().toString();
                b = Float.valueOf(a);
                c = (int) b;
                Bar.setProgress(c*2);
                ht.setText(String.valueOf(c*2));
                nhietdo.setText(a);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}