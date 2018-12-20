package com.eastcom.social.tinkerdemo;

import android.content.IntentFilter;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends AppCompatActivity {

    Button btn_loadpatch;
    TinkerUpdateReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_loadpatch = (Button) findViewById(R.id.loadPatch);
        btn_loadpatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receiver = new TinkerUpdateReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("BROADCAST_ACTION");
                registerReceiver(receiver, intentFilter);
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
            }
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
