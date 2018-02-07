/****************************************************************************************************/
/*                                                                                                  */
/*                 SOFE 4640 Mobile Application Development                                         */
/*                                                                                                  */
/*				   Final Project - Doc App                                                          */
/*                                                                                                  */
/*                 Instructor:  Dr. Randy Fortier                                                   */
/*                                                                                                  */
/*                 Date:  December 15, 2017                                                         */
/*                                                                                                  */
/*                                                                                                  */
/*       Group Members:                                                                             */
/*                                                                                                  */
/*      Cherlyne Santhirarajan 100540235                                                            */
/*      Shahrukh Zarir 100489271                                                                    */
/*      Robert Kocovski 100536625                                                                   */
/*                                                                                                  */
/*                                                                                                  */
/*                                                                                                  */
/****************************************************************************************************/




package com.example.a100536625.finalproject;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by 100536625 on 12/10/2017.
 */

public class MainActivity extends AppCompatActivity {

    private MediaPlayer musicMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // load a background song
        musicMediaPlayer = new MediaPlayer();
        Resources res = getResources();
        AssetFileDescriptor musicFd = res.openRawResourceFd(R.raw.slide);
        try {
            musicMediaPlayer.setDataSource(musicFd.getFileDescriptor(),
                    musicFd.getStartOffset(),
                    musicFd.getLength());

            musicMediaPlayer.prepare(); // blocking
            musicMediaPlayer.setLooping(true);
            musicMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


public void onSearch(View v) {
    Intent intent = new Intent(this, SearchDoctor.class);
    startActivity(intent);
}

public void onAppt(View v) {
    Intent intent = new Intent(this, MyAppointments.class);
    startActivity(intent);
}

public void onInfo(View v) {  //get prescription drug info from internet
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.drugs.com"));
    startActivity(intent);
}
}