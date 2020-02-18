package com.krishnamurthi.saratogahightrophyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.krishnamurthi.saratogahightrophyapplication.database.AppDatabase;
import com.krishnamurthi.saratogahightrophyapplication.database.Status;
import com.krishnamurthi.saratogahightrophyapplication.utils.Constants;

import java.io.File;

public class LaunchActivity extends AppCompatActivity {

    //TODO: Figure out the flags that go with the progress dialog text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        CSVDownloadTask task = new CSVDownloadTask(this);
        TextView progress = findViewById(R.id.progress_text);
        try {
            if(task.execute().get()) {
                for(String title : Constants.titles) {
                    String filePath = Environment.getExternalStorageDirectory().toString() + "cache/" + title + ".db";
                    if(new File(filePath).exists()) {
                        progress.setText("Finding Specific DB Changes");
                        AppDatabase.getInstance(getApplicationContext(), Status.CHANGES);
                    } else {
                        progress.setText("Creating New Database");
                        AppDatabase.getInstance(getApplicationContext(), Status.NEW);
                    }
                }
            } else {
                progress.setText("Instantiating DB with No Changes");
                AppDatabase.getInstance(getApplicationContext(), Status.NO_CHANGES);
                Intent i = new Intent(LaunchActivity.this, FullscreenActivity.class);
                startActivity(i);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
