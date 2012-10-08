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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registGcmButton = (Button) findViewById(R.id.regist_gcm_button);
        registGcmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GCMRegistrar.checkDevice(MainActivity.this);
                GCMRegistrar.checkManifest(MainActivity.this);
                String registrationId = GCMRegistrar.getRegistrationId(MainActivity.this);
                if ("".equals(registrationId)) {
                    GCMRegistrar.register(MainActivity.this, GCMIntentService.SENDER_ID);
                } else {
                    Toast.makeText(MainActivity.this, "既にGCM登録済み", Toast.LENGTH_SHORT).show();
                }
            }

        });

        Button unregistGcmButton = (Button) findViewById(R.id.unregist_gcm_button);
        unregistGcmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GCMRegistrar.unregister(MainActivity.this);
            }

        });
    }

}
