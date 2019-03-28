package com.jcv.audiorecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Inicio extends AppCompatActivity {

    Button start, stop;
    public MediaRecorder recorder = null;
    public String fileextn = ".mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        start = (Button) findViewById(R.id.start_button);
        stop = (Button) findViewById(R.id.stop_button);

        start.setOnClickListener(new View.OnClickListener(){
                                     @Override
                                     public void onClick(View v) {
                                         switch (v.getId()) {
                                             case R.id.start_button:
                                                 startRecord(); break;
                                             case R.id.stop_button:
                                                 stoprecord(); break;
                                         }
                                     }

                                 }
        );
    }

    private void stoprecord() {
        Toast.makeText(this,"presionaste stop",Toast.LENGTH_LONG).show();
        if (recorder != null) {
            recorder.stop();
            recorder.reset();

            recorder.release();
            recorder = null;
        }
    }

    private void startRecord() {
        Toast.makeText(this,"presionaste START",Toast.LENGTH_LONG).show();
        recorder = new MediaRecorder();
        recorder.reset();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(getFilePath());

        try {
            recorder.prepare();
            recorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, "MediaRecorderEjemplo");

        if (!file.exists())
            file.mkdirs();

        return (file.getAbsolutePath() + "/" + fileextn);
    }
}
