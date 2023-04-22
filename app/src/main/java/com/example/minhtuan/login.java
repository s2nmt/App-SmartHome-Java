package com.example.minhtuan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    Button btn_login;
    EditText pass;
    EditText account;
    TextView baoloi;
    DatabaseReference tk;
    DatabaseReference mk;
    ALodingDialog aLodingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = (Button) findViewById(R.id.button3);
        pass = findViewById(R.id.editTextTextPassword);
        account = findViewById(R.id.taikhoan);
        baoloi = findViewById(R.id.baoloi1);

        aLodingDialog = new ALodingDialog(this);


        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                aLodingDialog.show();

                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        aLodingDialog.cancel();
                    }
                };
                handler.postDelayed(runnable,1000);
                tk = FirebaseDatabase.getInstance().getReference();
                tk.child("Taikhoan").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue().toString().equals(account.getText().toString())){
                            mk = FirebaseDatabase.getInstance().getReference();
                            mk.child("Matkhau").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.getValue().toString().equals(pass.getText().toString())){
                                        baoloi.setText("");
                                        account.setText("");
                                        pass.setText("");
                                        Intent intent = new Intent(login.this,Home.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        baoloi.setText("Sai tai khoan hoac mat khau");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else{
                            baoloi.setText("Sai tai khoan hoac mat khau");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}