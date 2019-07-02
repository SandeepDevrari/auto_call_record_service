package in.co.one.angry.acrservice.services;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import in.co.one.angry.acrservice.database.DatabaseController;
import in.co.one.angry.acrservice.model.sPojoDataBase;
import in.co.one.angry.acrservice.sBase;

public class RecordIntentService extends IntentService {

    private String mFileName,mNumber;
    private Date mDate=new Date();
    private MediaRecorder mRecorder;
    private static final String LOG_TAG = "AudioRecord";
    private DatabaseController controller;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public RecordIntentService(String name) {
        super(name);
    }
    public RecordIntentService(){
        this("vodafone_service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            if (intent != null) {
                mNumber=intent.getStringExtra("NUMBER");
            }else{
                mNumber="0123456789";
            }
            startRecording();
            Log.e("########","Started");
            //onRecord(true);

        }catch (Exception e) {
            e.printStackTrace();
            Log.e("FILE", "Error Package name not found ", e);
        }
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

        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopRecording() {
        if (mRecorder!=null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            AudioManager audioManager=(AudioManager) getSystemService(AUDIO_SERVICE);
            if(audioManager!=null) {
                audioManager.setMode(AudioManager.MODE_NORMAL);
            }
        }
        if(controller!=null){
            sPojoDataBase pojoDataBase=new sPojoDataBase();
            pojoDataBase.setFileName(mFileName);
            pojoDataBase.setNumber(mNumber);
            if(controller.insert_file(pojoDataBase)){
                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
            }
            controller = null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mFileName= sBase.requestFileFolder(this);
            mFileName=mFileName+ File.separator+"."+mDate.getTime() + ".wav";
            controller=new DatabaseController(this);
        }catch (Exception e) {
            e.printStackTrace();
            Log.e("FILE", "Error Package name not found ", e);
        }
    }

    @Override
    public void onDestroy() {
        Log.e("###############","stoped");
        onRecord(false);
        super.onDestroy();
    }

}
