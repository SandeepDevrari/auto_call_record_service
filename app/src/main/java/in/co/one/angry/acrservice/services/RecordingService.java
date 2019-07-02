package in.co.one.angry.acrservice.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import in.co.one.angry.acrservice.database.DatabaseController;
import in.co.one.angry.acrservice.model.sPojoDataBase;
import in.co.one.angry.acrservice.sBase;

public class RecordingService extends Service {
    private String mFileName,mNumber;
    private Date mDate=new Date();
    private MediaRecorder mRecorder;
    private static final String LOG_TAG = "AudioRecord";
    private DatabaseController controller;
    private Thread thread;
    //private sPojoDataBase pojoDataBase;
    //private AudioRecord audioRecord;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    public RecordingService(String number, Date start){
////        mNumber=number;
////        mDate=start;
//    }
    public RecordingService(){
//        this("1234567899",new Date());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mFileName=sBase.requestFileFolder(this);
            mFileName=mFileName+File.separator+"."+mDate.getTime() + ".wav";
            controller=new DatabaseController(this);
            Log.e("Database ","controller created");


//            String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Android" + File.separator
//                    + "data" + File.separator + getPackageName();
//            File dir = new File(s);
//            if (dir.mkdir()) {
//                mFileName = mDate.toString() + ".wav";
//            } else {
//                Log.e("$$$", "No file");
//            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.e("FILE", "Error Package name not found ", e);
        }
//        File file=getExternalFilesDir(null);
//        if(file!=null) {
//            Log.e("$$$","file- "+file.getPath());
//            Toast.makeText(this, ""+file.getPath(), Toast.LENGTH_SHORT).show();
//            mFileName = file.getAbsolutePath();
//            mFileName += "/" + mDate.toString() + ".wav";
//        }else{
//            Log.e("$$$","No file");
//            Toast.makeText(this, "No file", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        mNumber=intent.getStringExtra("NUMBER");
        if(mNumber==null){
            mNumber="0123456789";
        }
        Log.e("##callrecordingstarted", mNumber+ "\tTime- " + mDate);

//        final Runnable runnableRecord = new Runnable() {
//            @Override
//            public void run() {
//                onRecord(true);
//                Log.e("Recorder","Recording started");
//                if () {
//                    Log.e("Thread","Thread Interrupted");
//                    onRecord(false);
//
//                }
//            }
//        };
        thread=new RecorderThread();
        thread.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("##callrecordingended",mNumber+"\tTime- "+new Date());
//        onRecord(false);
        thread.interrupt();
        onRecord(false);
        Log.e("Thread","Thread Interrupting");
        super.onDestroy();
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }
    private void startRecording() {
        AudioManager audioManager=(AudioManager) getSystemService(AUDIO_SERVICE);
        if(audioManager!=null) {
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL), 0);
        }
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);

        try {
            mRecorder.prepare();
            mRecorder.start();
            Log.e("Recorder","Recording..");
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopRecording() {
        try {
            if (mRecorder != null) {
                Log.e("Recorder", "Recording Ended");
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                if (audioManager != null) {
                    audioManager.setMode(AudioManager.MODE_NORMAL);
                }
            }
            if (controller != null) {
                sPojoDataBase pojoDataBase = new sPojoDataBase();
                pojoDataBase.setFileName(mFileName);
                pojoDataBase.setNumber(mNumber);
                if (controller.insert_file(pojoDataBase)) {
                    Log.e("Database", "File Inserted");
                    //Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Database", "File Failed!!");
                }
                controller = null;
            }
        }catch (Exception e){
            mRecorder=null;
        }
    }
    private class RecorderThread extends Thread{
        @Override
        public void run() {
            android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            onRecord(true);
            Log.e("Recorder","Recording started");
//            while(true){
//            if (Thread.interrupted()) {
//                Log.e("Thread","Thread Interrupted");
//                onRecord(false);
//            }else{
//                Log.e("Thread","***k");
//            }
//            }

        }
    }
}
