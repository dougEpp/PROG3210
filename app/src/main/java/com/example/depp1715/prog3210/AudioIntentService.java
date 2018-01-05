package com.example.depp1715.prog3210;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class AudioIntentService extends IntentService implements MediaPlayer.OnPreparedListener {

    public AudioIntentService() {
        super("AudioIntentService");
    }
    MediaPlayer mMediaPlayer;
    @Override
    protected void onHandleIntent(Intent intent) {
        mMediaPlayer = MediaPlayer.create(this, R.raw.birdsong);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.setOnPreparedListener(this);
    }

    /** Called when MediaPlayer is ready */
    public void onPrepared(MediaPlayer player) {
        player.start();
    }
}
