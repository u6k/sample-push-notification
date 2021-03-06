/*
Copyright (c) 2012 Yuichi Naono (u6k.yu1.main@gmail.com)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package jp.gr.java_conf.u6k.sample_gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

    // TODO SENDER_ID
    public static final String SENDER_ID = "xxx";

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
