
package jp.gr.java_conf.u6k.sample_gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

    public static final String SENDER_ID = "884497848039";

    public GCMIntentService() {
        super(SENDER_ID);
    }

    @Override
    protected void onRegistered(Context ctx, String registrationId) {
        Log.d("sample-gcm", "onRegistered: registrationId=" + registrationId);
        handleMessage("GCM登録");
    }

    @Override
    protected void onUnregistered(Context ctx, String registrationId) {
        Log.d("sample-gcm", "onUnregistered: registrationId=" + registrationId);
        handleMessage("GCM解除");
    }

    @Override
    protected void onError(Context ctx, String errorId) {
        Log.d("sample-gcm", "onError: errorId=" + errorId);
        handleMessage("GCMエラー");
    }

    @Override
    protected void onMessage(Context ctx, Intent intent) {
        String msg = intent.getStringExtra("message");

        Log.d("sample-gcm", "onMessage: msg=" + msg);
        handleMessage("GCM受信: msg=" + msg);
    }

    private void handleMessage(String msg) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification n = new Notification(android.R.drawable.btn_default, msg, System.currentTimeMillis());
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
        n.setLatestEventInfo(getApplicationContext(), "sample-gcm", msg, contentIntent);

        nm.notify(1, n);
    }

}
