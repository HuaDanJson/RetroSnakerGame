package com.example.zhangjinming.androidgame8.activity;

import android.os.Bundle;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.base.BaseActivity;
import com.example.zhangjinming.androidgame8.utils.PlayMusicManager;

public class MiddlePlayGameActivity extends BaseActivity {

    private MiddleGameView mMiddleGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMiddleGameView = new MiddleGameView(this);
        setContentView(mMiddleGameView);
        PlayMusicManager.getInstance().playMusic(this, R.raw.background_music);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayMusicManager.getInstance().stopMusic();
    }
}
