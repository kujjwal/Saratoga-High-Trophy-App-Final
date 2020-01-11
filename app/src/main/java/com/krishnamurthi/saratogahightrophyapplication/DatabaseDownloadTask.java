package com.krishnamurthi.saratogahightrophyapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

public class DatabaseDownloadTask extends AsyncTask<Void, Void, String[]> {
    private final String[] titles = {"sports", "trophies"};
    private WeakReference<Activity> foregroundActivity;

    private static final String DOWNLOAD_URL = "https://docs.google.com/spreadsheets/d/" +
            "1bCjaCRR1ezrEWUXnxYyPRpUK5nr6u3NTP7iEitLEyxo/export?gid=YOURGID&format=csv";
    private static final String[] GIDS = {"0", "2132257028"};

    DatabaseDownloadTask(Activity activity) {
        this.foregroundActivity = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(foregroundActivity.get() != null) {
            TextView progress = foregroundActivity.get().findViewById(R.id.progress_text);
            progress.setText(R.string.progress_bar_asynctask_dialog);
        }
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        try {
            String[] filePaths = new String[GIDS.length];
            for(int i = 0; i < GIDS.length; i++) {
                String url = DOWNLOAD_URL.replace("YOURGID", GIDS[i]);
                filePaths[i] = saveCSVs(url, titles[i]);
            }
            return filePaths;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);

    }

    private String saveCSVs(String string, String title) throws IOException {
        int count;
        URL url = new URL(string);
        URLConnection conn = url.openConnection();
        conn.connect();

        InputStream input = new BufferedInputStream(url.openStream(),8192);

        File csv = new File(Environment.getExternalStorageDirectory().toString() + title + ".csv");
        OutputStream output = new FileOutputStream(csv);

        byte[] data = new byte[1024];
        while ((count = input.read(data)) != -1) {
            output.write(data, 0, count);
        }

        output.flush();
        output.close();
        input.close();
        return csv.getAbsolutePath();
    }
}
