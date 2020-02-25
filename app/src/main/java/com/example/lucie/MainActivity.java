package com.example.lucie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucie.DisplayMessageActivity;
import com.example.lucie.R;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public static String google = "http:/google.com";
    public static String gmail = "http:/gmail.com";
    TextView play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView=findViewById(R.id.textView);


        play=findViewById(R.id.mp3Song);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,Design.class);
            startActivity(intent);
            }
        });
    }

    public void sendMessage(View view) {
        EditText message = (EditText) findViewById(R.id.message);
        Toast.makeText(this, "sending message" + message.getText().toString(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("MESSAGE", message.getText().toString());
        startActivity(intent);
        message.setText("");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.art, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem items) {
        int id = items.getItemId();
        switch (id) {
            case R.id.surf:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(google));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            case R.id.mail:
                Intent intents = new Intent(Intent.ACTION_VIEW, Uri.parse(gmail));
                if (intents.resolveActivity(getPackageManager()) != null) {
                    startActivity(intents);
                }
            case R.id.list1:
                startActivity(new Intent(this, ListViews.class));
                return true;

            case R.id.cfile:
                startActivity(new Intent(this, ReadCode.class));
                return true;
        }
        return super.onOptionsItemSelected(items);

    }
}
