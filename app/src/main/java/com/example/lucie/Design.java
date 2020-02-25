package com.example.lucie;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Design extends AppCompatActivity {
    Button pause;
    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        Button song = (Button)findViewById(R.id.buttonMP3);
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer player = MediaPlayer.create(Design.this,R.raw.church);
                player.start();
            }
        });
        pause = (Button)findViewById(R.id.stop);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPlayer != null && mPlayer.isPlaying()) {
                    mPlayer.stop();

                }
            }
        });
    }
}
