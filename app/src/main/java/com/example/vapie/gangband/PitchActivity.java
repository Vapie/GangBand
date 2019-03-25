package com.example.vapie.gangband;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Objects;

public class PitchActivity extends Activity {
    Spinner spinner;
    private static Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitch);



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
}

    public void gotoclavier(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
