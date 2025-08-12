package com.example.bai20;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextCha;

    public MyAsyncTask(Activity ctx) {
            contextCha = ctx;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        Toast.makeText(contextCha, "Update xong roi do!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        ProgressBar paCha=contextCha.findViewById(R.id.progressBar);
        int giatri = values[0];
        paCha.setProgress(giatri);
        TextView txtmsg = contextCha.findViewById(R.id.textView);
        txtmsg.setText(giatri+"%");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100);
            publishProgress(i);
        }
        return null;
    }
}
