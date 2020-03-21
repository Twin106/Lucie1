package com.example.lucie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra("level",0);
            ProgressBar progressBar= (ProgressBar)findViewById(R.id.progress);
            progressBar.setProgress(level);

            TextView textView= (TextView)findViewById(R.id.battery);
            textView.setText("Battery level:" + Integer.toString(level)+ "%");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(myReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
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
    public void setAlarm(View view){

        EditText text=(EditText)findViewById(R.id.second);
        int one = Integer.parseInt(text.getText().toString());

        //Create an intent and call your receiver
        Intent intent = new Intent(this,BroadcastReceiver.class);

        //Create a pending Intent to be fired
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(),0,intent,0);

        //Recall the alarm manager class
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Real time clock to be used

        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (one * 1000),pendingIntent);
        Toast.makeText(this,"Alarm set in" + one + "seconds",Toast.LENGTH_LONG).show();
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
            case R.id.send:
                Intent intent2 = new Intent(Intent.ACTION_SEND);
                intent2.setData(Uri.parse("mailto"));
                String to [] = {"nankundajiddah@gmail.com","gumisirizabrian@gmail.com","twinlucy64@gmail.com"};
                intent2.putExtra(Intent.EXTRA_EMAIL, to);
                intent2.putExtra(Intent.EXTRA_SUBJECT, "Invitation");
                intent2.putExtra(Intent.EXTRA_TEXT, "You are invited to come and pay me a visit in this Corona holiday");
                intent2.setType("message/rfc822");
                startActivity(intent2);
                return true;

            case R.id.call:
                try {
                    // check for call permissions
                    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

                    // Here, thisActivity is the current activity
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {

                            // Show an explanation to the user *asynchronously*
                            Toast.makeText(this, "This permission is required to call a phone number", Toast.LENGTH_LONG).show();

                        } else {

                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    }
                    Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0780704528"));
                    startActivity(in);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return true;
        }
        return super.onOptionsItemSelected(items);

    }
}
