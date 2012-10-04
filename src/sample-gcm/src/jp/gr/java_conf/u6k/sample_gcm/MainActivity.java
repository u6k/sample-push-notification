
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
