package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;


public class MainActivity extends NMapActivity {
    String ret= "";
    static int inx =0;
    boolean []disorder_category = new boolean[5];
    String [] category = new String[5];
    //-  당뇨, 고혈압, 위염/위궤앙, 장염
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView nameText = (TextView)findViewById(R.id.txt);
        Intent intent = getIntent();
        nameText.setText(intent.getStringExtra("nameText").toString());
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.check1) ;
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.check2) ;
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.check3) ;
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.check4) ;
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.check5) ;
        Button btn = (Button)findViewById(R.id.list_btn);



        checkBox1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                disorder_category[0]=true;
                category[inx++] = "diabetes";
            }
        }) ;
        checkBox2.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View view) {
                disorder_category[1]=true;
                category[inx++] = "blood";
            }
        });
        checkBox3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                disorder_category[2]=true;
                category[inx++] = "gastritis";
            }
        }) ;
        checkBox4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                disorder_category[3]=true;
                category[inx++] = "enteritis";
            }
        }) ;
        checkBox5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                disorder_category[4]=true;
                category[inx++] = "Hyperlipidemia";
            }
        }) ;
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for(int i=0; i<inx; i++) System.out.println(category[i]);
            }
        });

    }

}
