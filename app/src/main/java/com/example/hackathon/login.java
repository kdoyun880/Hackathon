package com.example.hackathon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText etx = (EditText)findViewById(R.id.etx_id);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID_txt= etx.getText().toString();
                /*
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = (View) inflater.inflate(R.layout.activity_main, null);
                setContentView(v);
                */
                Intent intent = new Intent(getApplicationContext(), TextBox.class);
                intent.putExtra("nameText", ID_txt);
                startActivity(intent);

            }
        });
    }
}
