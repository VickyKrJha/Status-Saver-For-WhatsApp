package com.vickysg.whatsappstatussaver.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import android.widget.MediaController;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import com.vickysg.whatsappstatussaver.R;


import java.io.File;

public class VideoView extends AppCompatActivity {
    MediaController mediaControls;
    android.widget.VideoView videoView;
    private InterstitialAd mInterstitialAd;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3912259549278001/5948264749", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                mInterstitialAd.show(VideoView.this);

            }
        });

        videoView = findViewById(R.id.video_view);
        Intent intent = getIntent();
        String file1 = intent.getStringExtra("file");
        File file = new File(file1);


        videoView.setVideoURI(Uri.parse(String.valueOf(file)));
        if (mediaControls == null) {
            mediaControls = new MediaController(VideoView.this);
            mediaControls.setAnchorView(videoView);
        }

        videoView.setMediaController(mediaControls);
        videoView.setVideoURI(Uri.parse(String.valueOf(file)));

        videoView.start();

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show();

                return false;
            }
        });



    }



}