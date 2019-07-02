package in.co.one.angry.acrservice.receiver;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import in.co.one.angry.acrservice.FileShowActivity;
import in.co.one.angry.acrservice.services.RecordingService;

public class CallRecorder extends CallReceiverBroadcast {

//    private static final String LOG_TAG = "AudioRecord";
//   // private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
//    private static String mFileName = null;
//
//    private MediaRecorder mRecorder = null;

//    private boolean permissionToRecordAccepted = false;
//    private String [] permissions = {Manifest.permission.RECORD_AUDIO};


//    @Override
//    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
//        Log.w("##Incomming Started",number+"\tTime- "+start);
//        Toast.makeText(ctx, "Incomming Started", Toast.LENGTH_SHORT).show();
//
//        mFileName = ctx.getExternalCacheDir().getAbsolutePath();
//        mFileName += "/"+start.toString()+".3gp";
//
//        onRecord(true);
//    }
//
//    @Override
//    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
//        Log.w("##Incomming Ended",number+"\tTime- "+start+"\tto Time- "+end);
//        onRecord(false);
//    }
//
//    @Override
//    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
//        Log.w("##Outgoing Started",number+"\tTime- "+start);
//        onRecord(true);
//    }
//
//    @Override
//    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
//        Log.w("##Outgoing Ended",number+"\tTime- "+start+"\tto Time- "+end);
//        onRecord(false);
//    }
//
//    @Override
//    protected void onMissedCall(Context ctx, String number, Date start) {
//        Log.w("##Missed Call",number+"\tTime- "+start);
//        onRecord(false);
//    }

    @Override
    protected void onCallReceived(Context context, String number, Date start) {
        //RecordingService recordingService=new RecordingService(number,start);
        //Toast.makeText(context, ""+number, Toast.LENGTH_SHORT).show();
//        if(number.equals("000*")){
//            context.startActivity(new Intent(context, FileShowActivity.class));
//        }else {
            Intent intent = new Intent(context, RecordingService.class);
            intent.putExtra("NUMBER", number);
            context.startService(intent);
//        File file=context.getExternalFilesDir(null);
//        if(file!=null) {
//            mFileName = file.getAbsolutePath();
//            mFileName += "/" + start.toString() + ".wav";
//            Log.e("##callrecordingstarted", number + "\tTime- " + start);
//            onRecord(true);
//        }
//        }
    }

    @Override
    protected void onCallEnd(Context context, String number, Date end) {
        context.stopService(new Intent(context,RecordingService.class));
//        Log.e("##callrecordingended",number+"\tTime- "+end);
//        onRecord(false);
    }

//    private void onRecord(boolean start) {
//        if (start) {
//            startRecording();
//        } else {
//            stopRecording();
//        }
//    }
//    private void startRecording() {
//        mRecorder = new MediaRecorder();
//        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        mRecorder.setOutputFile(mFileName);
//        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//        try {
//            mRecorder.prepare();
//            mRecorder.start();
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "prepare() failed");
//        }
//    }
//
//    private void stopRecording() {
//        if (mRecorder!=null) {
//            mRecorder.stop();
//            mRecorder.release();
//            mRecorder = null;
//        }
//    }
}
