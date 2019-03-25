package com.example.vapie.gangband;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class RecordActivity extends AppCompatActivity implements MediaRecorder.OnInfoListener {

        Context context;

        private static final String LOG_TAG = "AudioRecordTest";
        private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
        private static String fileName = null;

        private MediaRecorder recorder = null;

        private MediaPlayer player = null;

        // Requesting permission to RECORD_AUDIO
        private boolean permissionToRecordAccepted = false;
        private String [] permissions = {Manifest.permission.RECORD_AUDIO};

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode){
                case REQUEST_RECORD_AUDIO_PERMISSION:
                    permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    break;
            }
            if (!permissionToRecordAccepted ) finish();

        }

        private void onRecord(boolean start,String savename) {
            if (start) {
                startRecording(savename);
            } else {
            stopRecording();
            }
        }


        private void startRecording(String savename) {
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setOutputFile(fileName+"/"+savename+".mp3");
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);
            recorder.setMaxDuration(5000);
            recorder.setOnInfoListener(this);

            try {
                recorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }
            recorder.start();
        }

        public void onInfo(MediaRecorder mr, int what, int extra) {
            if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                Log.v("VIDEOCAPTURE","Maximum Duration Reached");
                mr.stop();
            }
        }

        private void stopRecording() {
            recorder.stop();
            recorder.release();
            recorder = null;
        }


        @Override
        public void onCreate(Bundle icicle) {

            String yourPath = Objects.requireNonNull(this.getExternalCacheDir()).getAbsolutePath();
            File directory = new File(yourPath);
            final String[] results = (directory.list(new FilenameFilter() {
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



            super.onCreate(icicle);
            setContentView(R.layout.activity_record);

            // Record to the external cache directory for visibility
            fileName = getExternalCacheDir().getAbsolutePath();

            ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

            final Button button = (Button) findViewById(R.id.rec);

            final EditText recordname=(EditText)findViewById(R.id.filename);


            button.setOnClickListener(new View.OnClickListener() {
                boolean mStartRecording = true;
                @Override
                public void onClick(View v) {
                    button.setEnabled(false);
                    Timer buttonTimer = new Timer();
                    buttonTimer.schedule(new TimerTask() {
// delay for supress button spam
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    button.setEnabled(true);
                                }
                            });
                        }
                    }, 300);

                    String recordnamestring = recordname.getText().toString();

                    boolean deja = false;
                    boolean vide = false;
                    for (String var : results){if(var.equals(recordnamestring)) {deja = true;}} // nom deja existant ??
                    if (recordnamestring.length() == 0){vide = true;}

                    if (deja||vide||recordnamestring.indexOf(' ')>=0||recordnamestring.indexOf('\\')>=0||recordnamestring.indexOf('/')>=0||recordnamestring.indexOf(',')>=0||recordnamestring.indexOf('?')>=0||recordnamestring.indexOf('.')>=0||recordnamestring.indexOf(';')>=0||recordnamestring.indexOf(':')>=0||recordnamestring.indexOf('!')>=0||recordnamestring.indexOf('\'')>=0) {
                        String errormsg = "forbidden characters in filename";
                        if (vide)
                            errormsg = "filename can't be empty";
                        else if(deja)
                            errormsg = "filename allready exists";
                        Toast.makeText(getApplicationContext(), errormsg, Toast.LENGTH_SHORT).show();


                    }else {
                        Toast.makeText(getApplicationContext(), String.valueOf(recordnamestring.indexOf(' ')), Toast.LENGTH_SHORT).show();
                        onRecord(mStartRecording,recordnamestring);

                    if (mStartRecording) {
                        button.setText("Stop recording");

                    } else {
                        button.setText("Start recording");




                    }
                    mStartRecording = !mStartRecording;
                }
                }


                });



        }

    public void supress(View view) {
        String yourPath = Objects.requireNonNull(this.getExternalCacheDir()).getAbsolutePath();
        File directory = new File(yourPath);
        final String[] results = (directory.list(new FilenameFilter() {
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
        final EditText recordname=(EditText)findViewById(R.id.filename);
        String recordnamestring = recordname.getText().toString();
        boolean exist = false;
        for (String var : results){if(var.equals(recordnamestring)) {exist = true;}} // nom deja existant ??
        if (exist){
            File file = new File(yourPath+"/"+recordnamestring+".mp3");
            file.delete();
            recordname.setText("");
            Toast.makeText(getApplicationContext(), "File "+recordnamestring+" sucessfuly supressed", Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(getApplicationContext(), "Unknown file name", Toast.LENGTH_SHORT).show();

    }
    public void gotoclavier(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

        @Override
        public void onStop() {
            super.onStop();
            if (recorder != null) {
                recorder.release();
                recorder = null;
            }

            if (player != null) {
                player.release();
                player = null;
            }
        }

    }

