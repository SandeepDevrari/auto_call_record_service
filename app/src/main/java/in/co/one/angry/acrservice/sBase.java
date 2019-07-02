package in.co.one.angry.acrservice;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class sBase {
    private static final String LOG_TAG=sBase.class.getSimpleName();
    public static final int WRITE_PERMISSION=101;
    public static final int READ_PHONE_STATE_PERMISSION=102;
    public static final int RECORD_AUDIO_PERMISSION=103;
    public static final int OUTGOING_CALL_PERMISSION=104;

    public static  boolean isWRITE_PERMISSION=false;
    public static boolean isREAD_PHONE_STATE_PERMISSION=false;
    public static boolean isRECORD_AUDIO_PERMISSION=false;
    public static boolean isOUTGOING_CALL_PERMISSION=false;
    public static boolean isFOLDER_CREATED=false;
    public static final String DB_NAME="ACRDatabase.db";
    public static final int VERSION=1;
    public static final String TABLE_ACR="TABLE_ACR";
    public static final String FILE_NAME="FILE_NAME";
    public static final String DATE="FILE_DATE";
    public static final String IS_SYNC="IS_SYNC";
    public static final String FILE_NUMBER="FILE_NUMBER";
    public static final String FILE_ID="FILE_ID";
    public static final String TABLE_QUERY="create table "+TABLE_ACR+"("+FILE_ID+" integer primary key autoincrement,"
            +FILE_NAME+" text,"
            +FILE_NUMBER+" text,"
            +DATE+" datetime default current_timestamp,"+IS_SYNC+" boolean default false)";
    public static final String TABLE_READ_DATA_QUERY="select * from "+TABLE_ACR+" order by "+DATE+" desc";


    public static void requestWrite(Context activity){

        ArrayList<String> permissions = new ArrayList<>();
        if(ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.PROCESS_OUTGOING_CALLS)!= PackageManager.PERMISSION_GRANTED){
            permissions.add(Manifest.permission.PROCESS_OUTGOING_CALLS);
        }
        if(ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            permissions.add(Manifest.permission.RECORD_AUDIO);
        }
        int count = permissions.size();
        if (count > 0) {
            String[] per = new String[count];
            for (int x = 0; x < count; x++) {
                per[x] = permissions.get(x);
            }
            ActivityCompat.requestPermissions((Activity) activity, per, RECORD_AUDIO_PERMISSION);
        }

        //ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.RECORD_AUDIO},RECORD_AUDIO_PERMISSION);
    }
    public static String requestFileFolder(Context activity){

        String folder_main ="."+activity.getResources().getString(R.string.app_name);

        File f = new File(Environment.getExternalStorageDirectory()+File.separator+folder_main);
        if (!f.exists()) {
            if(f.mkdirs()){
                Log.i(LOG_TAG,"FOLDER CREATED");
                isFOLDER_CREATED=true;
            }else{
                Log.i(LOG_TAG,"UNABLE TO CREATE FOLDER");
                isFOLDER_CREATED=false;
                return null;
            }
        }else{
            isFOLDER_CREATED=true;
        }
        return f.getAbsolutePath();
//        File file = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), mFileName);
//        mFileName=file.getAbsolutePath();
//        if (!file.mkdirs()) {
//            Log.e(LOG_TAG, "Directory not created");
//        }
    }
}
