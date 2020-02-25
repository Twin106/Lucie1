package com.example.lucie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class ReadCode extends AppCompatActivity {
TextView code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_code);
        code=findViewById(R.id.file);
        String  txt ="";

        try {
            InputStream inputStream = getAssets().open("main.c");
            int size = inputStream.available();
            byte[]buffer=new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            txt=new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        code.setText((CharSequence)txt);
    }
}
