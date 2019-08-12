package com.guestlogix.marianozorrilla.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract void init();
    public abstract int getLayout();
    public abstract void toCreate(Bundle savedInstanceStat);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        toCreate(savedInstanceState);
        init();
    }
}