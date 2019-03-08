package com.example.tapiev.gangband;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    String name;

    public void gopitch(View view) {
        Intent intent=new Intent(this, PitchActivity.class);
        startActivity(intent);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
    public void oof(View view) {
        new Task1(R.raw.oof).execute();
    }
    public void ooof(View view) {
        new Task1(R.raw.oof,2.0f).execute();
    }
    public void do1(View view) {
        new Task1(R.raw.oof).execute();
    }
    public void re1(View view) {
        new Task1(R.raw.oof,1.166f).execute();
    }
    public void mi1(View view) {
        new Task1(R.raw.oof,1.333f).execute();
    }
    public void fa1(View view) {
        new Task1(R.raw.oof,1.416f).execute();
    }
    public void sol1(View view) {
        new Task1(R.raw.oof,1.583f).execute();
    }
    public void la1(View view) {
        new Task1(R.raw.oof,1.75f).execute();
    }
    public void si1(View view) {
        new Task1(R.raw.oof,1.916f).execute();
    }









    @SuppressLint("StaticFieldLeak")
    class Task1 extends AsyncTask<Void, Void, String> {

        int soundR;
        float pitch;
        Task1(int rSound){
            this.soundR = rSound;
            this.pitch =1.0f;

        }
        Task1(int rSound,float pitchChange){
            this.soundR = rSound;
            this.pitch =pitchChange;


        }
        @Override
        protected void onPreExecute()
        {
            mediaPlayer = MediaPlayer.create(MainActivity.this, soundR);
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... arg0)
        {
            mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setPitch(pitch));
            mediaPlayer.start();
            return "";
        }

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
/*
public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener  {
    private static final String ACTION_PLAY = "com.example.action.PLAY";
    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.oof);
    }

    public void oof(View view) {
        mediaPlayer.start();
    }

    public void ooof(View view) {
        mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setPitch(0.5f));
        mediaPlayer.start();
    }
    public void onPrepared(MediaPlayer player) {
        player.start();
    }
}
*/