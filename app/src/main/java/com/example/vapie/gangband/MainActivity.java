package com.example.vapie.gangband;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    MediaPlayer mediaPlayer;
    String name;
    String playedsample="/";
    String skelet = null;
    private static Context context;




//If this pathname does not denote a directory, then listFiles() returns null.


    public void gopitch(View view) {
        Intent intent=new Intent(this, PitchActivity.class);
        startActivity(intent);

    }
    public void gorecord(View view) {
        Intent intent=new Intent(this, RecordActivity.class);
        startActivity(intent);
        finish();
    }
    public void gomap(View view) {
        Intent intent=new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        String shoestring = Objects.requireNonNull(this.getExternalCacheDir()).getAbsolutePath();
        MainActivity.context= getApplicationContext(); // contexte de l'applicatio
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String yourPath = Objects.requireNonNull(this.getExternalCacheDir()).getAbsolutePath();
        File directory = new File(yourPath);
        String[] results = (directory.list(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return fileName.endsWith(".mp3");
            }
        }));
        int i=0;
        for (String var : results)
        {
            results[i]=var.replace(".mp3","");
            i++;
        }

        spinner =  findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                results
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                playedsample = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                Toast.makeText(getApplicationContext(), "Selected : " + playedsample, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                 playedsample = "/";
            }

// reload spinner ??

        });
    }

    public void do1(View view) {
        if (playedsample!= "/") new Task1(playedsample).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void re1(View view) {
        if (playedsample!= "/") new Task1(playedsample,1.166f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void mi1(View view) {
        if (playedsample!= "/") new Task1(playedsample,1.333f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void fa1(View view) {
        if (playedsample!= "/")  new Task1(playedsample,1.416f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void sol1(View view) {if (playedsample!= "/")  new Task1(playedsample,1.583f).execute();
    else
        Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();}
    public void la1(View view) {
        if (playedsample!= "/")  new Task1(playedsample,1.75f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void si1(View view) {
        if (playedsample!= "/")   new Task1(playedsample,1.916f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void do2(View view) { if (playedsample!= "/") new Task1(playedsample,2.0f).execute();
    else
        Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();}
    public void re2(View view) {
        if (playedsample!= "/")  new Task1(playedsample,2.166f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void mi2(View view) {
        if (playedsample!= "/")  new Task1(playedsample,2.333f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void fa2(View view) {
        if (playedsample!= "/")  new Task1(playedsample,2.416f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void sol2(View view) {
        if (playedsample!= "/")  new Task1(playedsample,2.583f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void la2(View view) {if (playedsample!= "/") new Task1(playedsample,2.75f).execute();
    else
        Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();}
    public void si2(View view) {
        if (playedsample!= "/") new Task1(playedsample,2.916f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void reb1(View view) {if (playedsample!= "/") new Task1(playedsample,1.084f).execute();
    else
        Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();}
    public void mib1(View view) {if (playedsample!= "/") new Task1(playedsample,1.416f).execute();
    else
        Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();}
    public void solb1(View view) { if (playedsample!= "/") new Task1(playedsample,1.5f).execute();
    else
        Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();}
    public void lab1(View view) {if (playedsample!= "/") new Task1(playedsample,1.666f).execute();
    else
        Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();}
    public void sib1(View view) {if (playedsample!= "/") new Task1(playedsample,1.833f).execute();
    else
        Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();}
    public void reb2(View view) {
        if (playedsample!= "/") new Task1(playedsample,2.084f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void mib2(View view) {
        if (playedsample!= "/")   new Task1(playedsample,2.416f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void solb2(View view) {if (playedsample!= "/")  new Task1(playedsample,2.5f).execute();
    else
        Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();}
    public void lab2(View view) {
        if (playedsample!= "/") new Task1(playedsample,2.666f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }
    public void sib2(View view) {
        if (playedsample!= "/")  new Task1(playedsample,2.833f).execute();
        else
            Toast.makeText(getApplicationContext(), "please record or select a valid file", Toast.LENGTH_SHORT).show();
    }





    @SuppressLint("StaticFieldLeak")
    class Task1 extends AsyncTask<Void, Void, String> {

        //int soundR;
        float pitch;
        String path;
       /* Task1(int rSound){
            this.soundR = rSound;
            this.pitch =1.0f;

        }
        Task1(int rSound,float pitchChange){
            this.soundR = rSound;
            this.pitch =pitchChange;


        }*/
        Task1(String filename,float pitchChange){
            this.path = (context.getExternalCacheDir()).getAbsolutePath()+"/"+filename+".mp3";
            this.pitch =pitchChange;
        }

        Task1(String filename){
            this.path = (context.getExternalCacheDir()).getAbsolutePath()+"/"+filename+".mp3";
            this.pitch =1.0f;
        }
        @Override
        protected void onPreExecute()
        {
            mediaPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(path));
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... arg0)
        {
            mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setPitch(pitch));
            mediaPlayer.start();
            Toast.makeText(context, path,  Toast.LENGTH_SHORT).show();
            return "";
        }
/*https://stackoverflow.com/questions/16541881/java-android-how-to-give-media-player-a-string-value-to-a-resource*/
        @Override
        protected void onPostExecute(String result)
        {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    try {
                        Context context = getApplicationContext();
                        CharSequence text = "Stop";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        mediaPlayer.release();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            super.onPostExecute(result);

        }

    }

}
