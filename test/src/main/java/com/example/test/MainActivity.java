package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by ouyangshen on 2017/10/28.
 */
public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_version).setOnClickListener(this);
        findViewById(R.id.btn_encrypt).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_version) {
            Intent intent = new Intent(this, VersionActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_encrypt) {
            Intent intent = new Intent(this, EncryptActivity.class);
            startActivity(intent);
        }
    }

}
