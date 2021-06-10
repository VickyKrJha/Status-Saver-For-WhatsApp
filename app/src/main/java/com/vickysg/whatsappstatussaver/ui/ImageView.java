package com.vickysg.whatsappstatussaver.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.vickysg.whatsappstatussaver.R;

import java.io.File;

public class ImageView extends AppCompatActivity {
    public android.widget.ImageView myImage;

    private InterstitialAd mInterstitialAd;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3912259549278001/5948264749", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                mInterstitialAd.show(ImageView.this);

            }
        });

        Intent intent=getIntent();
       String file1= intent.getStringExtra("file");
       File file=new File(file1);

        if(file.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

           myImage = findViewById(R.id.image_view1);

            myImage.setImageBitmap(myBitmap);

        }
    }
}