package com.example.lucie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tone extends AppCompatActivity implements View.OnClickListener{

    private Button bStart;
    private  Button bStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tone);
        bStart= findViewById(R.id.button2);
        bStop=findViewById(R.id.button);

        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==bStart){
           startService(new Intent(this, Ring.class));

        }
        else if (v==bStop){
           stopService(new Intent(this, Ring.class));

        }

    }
}
