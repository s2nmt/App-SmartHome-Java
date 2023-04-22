package com.example.minhtuan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class mainlightkc extends AppCompatActivity {
    SeekBar pwm;
    GraphView graph;
    double y;
    int a;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference pwm1 = database.getReference("PWM");
    DatabaseReference getpwm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlightkc);
        getSupportActionBar().hide();
        pwm = findViewById(R.id.seekbarpwm);

        getpwm =  FirebaseDatabase.getInstance().getReference();
        getpwm.child("PWM").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                a = Integer.parseInt(snapshot.getValue().toString());
                pwm.setProgress(a/2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        pwm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //i giá trị seebar
                pwm1.setValue(i*2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //GetData(urlGetData);

            }
        });
        graph = (GraphView) findViewById(R.id.graph);


        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        for (int x=0;x<50;x++) {
            y = (float) Math.sin(x);
            series.appendData(new DataPoint(x,y),true,90);
        }

        series.setColor(Color.rgb(0,80,100));
        series.setTitle("Curve 1");
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(5);
        series.setThickness(2);

        graph.addSeries(series);

        //Назва графіка
        graph.setTitle("Expenses");
        graph.setTitleTextSize(50);
        graph.setTitleColor(Color.RED);
        //Легенда
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        //Підписи осей
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        ((GridLabelRenderer) gridLabel).setHorizontalAxisTitle("X Axis Title");
        gridLabel.setVerticalAxisTitle("Y Axis Title");
    }
}