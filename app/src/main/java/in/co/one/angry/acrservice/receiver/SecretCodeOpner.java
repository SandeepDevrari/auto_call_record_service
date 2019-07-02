package in.co.one.angry.acrservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import in.co.one.angry.acrservice.FileShowActivity;

public class SecretCodeOpner extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String pwd = intent.getData().getHost();
        //Toast.makeText(context, "OOOOOOOOOO", Toast.LENGTH_SHORT).show();
        if(pwd.equals("666")) {
            Intent i = new Intent(context, FileShowActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
