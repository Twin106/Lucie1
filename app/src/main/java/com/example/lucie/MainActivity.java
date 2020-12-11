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
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucie.DisplayMessageActivity;
import com.example.lucie.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button btnsave;
    EditText etinput;
    String filename="";
    String filepath="";
    String filecontent="";

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

        btnsave=findViewById(R.id.btnsave);
        etinput=findViewById(R.id.etinput);
        filename="myFile.text";
        filepath="MyFileDir";
        if(isExternalStorageAvailableForRW()){
            btnsave.setEnabled(true);
        }
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filecontent=etinput.getText().toString().trim();
                if (!filecontent.equals("")){
                    File myExternalFile=new File(getExternalFilesDir(filepath),filename);
                    FileOutputStream fos=null;
                    try {
                        fos=new FileOutputStream(myExternalFile);
                        fos.write(filecontent.getBytes());
                    }catch (FileNotFoundException e){
                        e.printStackTrace();

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    etinput.setText("");
                    Toast.makeText(getApplicationContext(),"saved on the External Memory",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Text field can't be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isExternalStorageAvailableForRW(){
        String extStorage=Environment.getExternalStorageState();
        if(extStorage.equals(Environment.MEDIA_MOUNTED)){
            return  true;
        }
        return false;
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

            case R.id.tid:
                startActivity(new Intent(this, Tone.class));
        }
        return super.onOptionsItemSelected(items);

    }

    public void saveimage(View view){
        saveReceivedImage("image");
    }
    private void saveReceivedImage(String imageName){
        try {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, numberOfBytes);
            Drawable drawable= ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_launcher_background);
            Bitmap bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);

            File path = new File(MainActivity.this.getFilesDir(), "AndroidProject" + File.separator + "Images");
            if(!path.exists()){
                path.mkdirs();
            }
            File outFile = new File(path, imageName + ".jpeg");
            FileOutputStream outputStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
            Toast.makeText(this, "image stored on internal memory ", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Log.e("msg", "Saving received message failed with", e);
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void creatBitMap() {

        File Med = new File(Environment.getExternalStorageDirectory(), "myDirName");
        if (!Med.exists()) {
            if (Med.mkdir()) {
                Toast.makeText(this, "Directory Created", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("App", "Err");
            }
        }
    }
}
