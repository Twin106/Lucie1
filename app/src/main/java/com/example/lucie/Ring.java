package com.example.lucie;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class Ring extends Service {
    private MediaPlayer mime;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand( Intent t, int mute, int sid) {
        mime=MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mime.setLooping(true);
        mime.start();
        return START_STICKY;
    }
    public void onDestroy(){
        super.onDestroy();
        mime.stop();
    }
}
