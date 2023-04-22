package com.example.minhtuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KitchenFragment extends Fragment {


    Switch sw;
    ImageView img,imgcanhbao;
    Button btn_lightkc;
    TextView kq;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference lkc = database.getReference("PHONG_BEP/DEN");
    DatabaseReference lkc1;
    DatabaseReference ketqua;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kitchen, container, false);
        img = (ImageView) view.findViewById(R.id.imglightkc1);
        sw = (Switch) view.findViewById(R.id.swdenkc1);
        btn_lightkc = view.findViewById(R.id.btn_lightkc);
        kq = view.findViewById(R.id.kq);
        imgcanhbao = view.findViewById(R.id.imgcanhbao);

        ketqua = FirebaseDatabase.getInstance().getReference();
        ketqua.child("PHONG_BEP/Canhbao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().toString().equals("1")){
                    kq.setText("Nguy hiểm");
                    imgcanhbao.setImageResource(R.drawable.img_32);
                }
                else{
                    kq.setText("An toàn");
                    imgcanhbao.setImageResource(R.drawable.img_33);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lkc1 =  FirebaseDatabase.getInstance().getReference();
        lkc1.child("PHONG_BEP/DEN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("1")){
                    img.setImageResource(R.drawable.lighton);
                    sw.setChecked(true);
                }
                else if (snapshot.getValue().toString().equals("0"))
                {
                    img.setImageResource(R.drawable.lightoff);
                    sw.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw.isChecked()){
                    //led1.setValue("1");
                    lkc.setValue(1);
                    img.setImageResource(R.drawable.lighton);
                }
                else {
                    lkc.setValue(0);
                    //led1.setValue("0");
                    img.setImageResource(R.drawable.lightoff);
                }
            }
        });
        btn_lightkc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),mainlightkc.class);
                startActivity(intent);
            }
        });


        return view;
    }
}