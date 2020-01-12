package com.krishnamurthi.saratogahightrophyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        DatabaseDownloadTask task = new DatabaseDownloadTask(this);
        TextView progress = findViewById(R.id.progress_text);
        try {
            if(task.execute().get()) {
                // There are changes to the spreadsheets, so recreate the DB
                progress.setText(R.string.progress_bar_database_recreation);
                DatabaseManager.createDB();
            } else { startActivity(new Intent(LaunchActivity.this, FullscreenActivity.class)); }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
