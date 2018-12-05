package com.example.zhangjinming.androidgame8.activity;

import android.os.Bundle;
import android.view.View;

import com.example.zhangjinming.androidgame8.base.BaseActivity;

public class PlayGamesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
