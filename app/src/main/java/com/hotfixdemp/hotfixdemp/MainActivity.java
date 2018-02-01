package com.hotfixdemp.hotfixdemp;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    TextView tv_name;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        tv_name = (TextView) findViewById(R.id.tv_name);
        btn = (Button) findViewById(R.id.btn);
        tv_name.setText("已经修复");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk";
                File patchFile = new File(path);
                Log.i("TEST", "path-=-=>" + path);
                if (patchFile.exists() && patchFile.length() > 0) {
                    Log.i("TEST", "-=-=>正在修复");

//                    TinkerInstaller.onReceiveUpgradePatch(getApplication(), patchPath);

                    TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
                }

                Toast.makeText(MainActivity.this, "已经修复", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "我是帅比", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public String getCachePath(Context applicationContext) {
        File cacheDir;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {

            Log.i("TEST", "有sd卡");
            cacheDir = applicationContext.getExternalCacheDir();
        } else {
            Log.i("TEST", "无sd卡");
            cacheDir = applicationContext.getCacheDir();
        }

        if (!cacheDir.exists())
            cacheDir.mkdirs();

        Log.i("TEST", "cacheDir.getAbsolutePath()" + cacheDir.getAbsolutePath());
        return cacheDir.getAbsolutePath();
    }
}
