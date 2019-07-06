package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;


public class MainActivity extends NMapActivity {
    String ret= "";
    boolean []disorder_category = new boolean[5];
    String [] category = new String[5];
    //-  당뇨, 고혈압, 위염/위궤앙, 장염
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.check1) ;
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.check2) ;
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.check3) ;
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.check4) ;
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.check5) ;


        checkBox1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                disorder_category[0]=true;
                category();
            }
        }) ;
        checkBox2.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View view) {
                disorder_category[1]=true;
                category();
            }
        });
        checkBox3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                disorder_category[2]=true;
                category();
            }
        }) ;
        checkBox4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                disorder_category[3]=true;
                category();
            }
        }) ;
        checkBox5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                disorder_category[4]=true;
                category();
            }
        }) ;

    }
    public void category(){
        int inx =0;
     if(disorder_category[0]==true)
         category[inx++] = "diabetes";

     else if(disorder_category[1]==true)
         category[inx++] = "blood";
     else if(disorder_category[2]==true)
         category[inx++] = "gastritis";
     else if(disorder_category[3]==true)
         category[inx++] = "enteritis";
     else
         category[inx++] = "Hyperlipidemia";
    }


}
