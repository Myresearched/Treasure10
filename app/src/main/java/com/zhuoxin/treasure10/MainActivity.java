package com.zhuoxin.treasure10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private long exiTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime-exiTime>2000) {
            exiTime = currentTime;
            Toast.makeText(this, "再次返回退出", Toast.LENGTH_SHORT).show();
        }else {
            super.onBackPressed();
        }
    }
}
